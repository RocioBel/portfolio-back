package com.portfolio.repository;

import com.portfolio.model.JobExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJobExperienceRepository extends JpaRepository<JobExperience, Integer> {
}
