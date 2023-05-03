package com.portfolio.repository;

import com.portfolio.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IEducationRepository extends JpaRepository<Education, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Education e WHERE e.educationId = :id")
    void deleteEducation(Integer id);
}
