package com.nunovieira.FirstServe.common;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nunovieira.FirstServe.common.enums.JobType;

@Repository 
public interface SyncJobRepository extends JpaRepository<SyncJob, Long>{
    Optional<SyncJob> findByType(JobType type);
}
