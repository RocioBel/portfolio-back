package com.portfolio.repository;

import com.portfolio.model.JobExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IJobExperienceRepository extends JpaRepository<JobExperience, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM JobExperience e WHERE e.experienceId = :id")
    void deleteExperience(Integer id);
}
