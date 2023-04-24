package com.portfolio.repository;

import com.portfolio.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IProjectRepository extends JpaRepository<Project, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Project e WHERE e.projectId = :id")
    void deleteProject(Integer id);
}
