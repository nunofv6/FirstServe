package com.nunovieira.FirstServe.client;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nunovieira.FirstServe.client.livetennis.LiveTennisClient;
import com.nunovieira.FirstServe.client.livetennis.dto.LiveTennisPlayerDto;
import com.nunovieira.FirstServe.client.livetennis.dto.LiveTennisPlayersResponse;
import com.nunovieira.FirstServe.common.SyncJob;
import com.nunovieira.FirstServe.common.SyncJobRepository;
import com.nunovieira.FirstServe.common.enums.JobStatus;

@Service 
public class Synchronizer {

    private static final Integer PLAYERS_LIMIT = 200;
    private static final Integer DUMMY_API_LIMIT = 10;

    private final LiveTennisClient liveTennisClient;
    private final PlayerSaver playerSaver;
    private final SyncJobRepository syncJobRepository;

    public Synchronizer(LiveTennisClient liveTennisClient, PlayerSaver playerSaver, SyncJobRepository syncJobRepository) {
        this.liveTennisClient = liveTennisClient;
        this.playerSaver = playerSaver;
        this.syncJobRepository = syncJobRepository;
    }

    public void syncPlayers(SyncJob syncJob) {

        if (syncJob.getStatus() == JobStatus.IDLE) {
            syncJob.setLastStarted(Instant.now());
            syncJob.setStatus(JobStatus.IN_PROGRESS);
        }

        LiveTennisPlayersResponse response;
        Integer apiCallCount = 0;
        do {
            response = liveTennisClient.getPlayers(PLAYERS_LIMIT, syncJob.getNextOffset());

            apiCallCount++;

            List<LiveTennisPlayerDto> dtos = response.data()
                    .stream()
                    .filter(dto -> !Boolean.TRUE.equals(dto.is_doubles_team()))
                    .toList();

            syncJob.setNextOffset(syncJob.getNextOffset() + response.meta().count());

            playerSaver.upsertPlayers(dtos);
            syncJobRepository.save(syncJob);
        }
        while (Boolean.TRUE.equals(response.meta().has_more()) && apiCallCount <= DUMMY_API_LIMIT);

        if (Boolean.FALSE.equals(response.meta().has_more())) {
            syncJob.setNextOffset(0);
            syncJob.setLastSynced(Instant.now());
            syncJob.setStatus(JobStatus.IDLE);
            syncJobRepository.save(syncJob);
        }
    }
}
