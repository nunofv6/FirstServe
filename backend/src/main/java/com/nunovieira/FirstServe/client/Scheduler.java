package com.nunovieira.FirstServe.client;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.nunovieira.FirstServe.common.SyncJob;
import com.nunovieira.FirstServe.common.SyncJobRepository;
import com.nunovieira.FirstServe.common.enums.JobStatus;

@Service 
public class Scheduler {
    
    private final Synchronizer synchronizer;
    private final SyncJobRepository syncJobRepository;

    public Scheduler(Synchronizer synchronizer, SyncJobRepository syncJobRepository) {
        this.synchronizer = synchronizer;
        this.syncJobRepository = syncJobRepository;
    }

    @Scheduled(timeUnit = TimeUnit.MINUTES, fixedDelay = 15)
    public void checkSync() {
        List<SyncJob> jobs = syncJobRepository.findAll();

        for (SyncJob job : jobs) {
            switch(job.getType()) {
                case MATCHES:
                    if ((job.getStatus() == JobStatus.IDLE && job.getLastSynced().isBefore(Instant.now().minus(Duration.ofDays(7)))) || job.getStatus() == JobStatus.IN_PROGRESS) {
                        synchronizer.syncPlayers();
                    }
                    
                case TOURNAMENTS:
                    break;

                case PLAYERS:
                    break;
            }
        }
    }
}
