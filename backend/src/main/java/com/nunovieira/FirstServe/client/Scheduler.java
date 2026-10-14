package com.nunovieira.FirstServe.client;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.nunovieira.FirstServe.common.SyncJob;
import com.nunovieira.FirstServe.common.SyncJobRepository;
import com.nunovieira.FirstServe.common.enums.JobStatus;
import com.nunovieira.FirstServe.common.enums.JobType;

@Service 
public class Scheduler {
    
    private final Synchronizer synchronizer;
    private final SyncJobRepository syncJobRepository;

    public Scheduler(Synchronizer synchronizer, SyncJobRepository syncJobRepository) {
        this.synchronizer = synchronizer;
        this.syncJobRepository = syncJobRepository;
    }

    @Scheduled(timeUnit = TimeUnit.DAYS, fixedDelay = 7)
    public void checkSync() {

        for (JobType type : JobType.values()) {
            SyncJob job = syncJobRepository.findByType(type)
                        .orElseGet(() -> new SyncJob(type, JobStatus.IDLE, null, null, 0));

            switch(type) {
                case PLAYERS:
                    if (job.getLastSynced() == null || (job.getStatus() == JobStatus.IDLE && job.getLastSynced().isBefore(Instant.now().minus(Duration.ofDays(7)))) || job.getStatus() == JobStatus.IN_PROGRESS) {
                        synchronizer.syncPlayers(job);
                    }
                    
                case TOURNAMENTS:
                    break;

                case MATCHES:
                    break;
            }
        }
    }
}
