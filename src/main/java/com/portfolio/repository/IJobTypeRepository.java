package com.portfolio.repository;

import com.portfolio.model.JobType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IJobTypeRepository extends JpaRepository<JobType, Integer> {
}
