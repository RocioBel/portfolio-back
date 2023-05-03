package com.portfolio.repository;

import com.portfolio.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ISkillRepository extends JpaRepository<Skill, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Skill s WHERE s.skillId = :id")
    void deleteSkill(Integer id);
}
