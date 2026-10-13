package com.nunovieira.FirstServe.common;

import java.time.Instant;

import com.nunovieira.FirstServe.common.enums.JobStatus;
import com.nunovieira.FirstServe.common.enums.JobType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table (name = "sync_jobs")
public class SyncJob {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private JobType type;

    private JobStatus status;
    private Instant lastSynced;
    private Instant lastStarted;
    private Integer offset;

    public SyncJob(JobType type, JobStatus status, Instant lastSynced, Instant lastStarted, Integer offset) {
        this.type = type;
        this.status = status;
        this.lastSynced = lastSynced;
        this.lastStarted = lastStarted;
        this.offset = offset;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobType getType() {
        return type;
    }

    public void setType(JobType type) {
        this.type = type;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public Instant getLastSynced() {
        return lastSynced;
    }

    public void setLastSynced(Instant lastSynced) {
        this.lastSynced = lastSynced;
    }

    public Instant getLastStarted() {
        return lastStarted;
    }

    public void setLastStarted(Instant lastStarted) {
        this.lastStarted = lastStarted;
    }

    public Integer getOffset() {
        return offset;
    }
    
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

}
