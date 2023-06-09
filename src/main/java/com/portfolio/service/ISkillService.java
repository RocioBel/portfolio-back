package com.portfolio.service;

import com.portfolio.dto.PersonDto;
import com.portfolio.dto.SkillDto;
import com.portfolio.exception.EntityNotFoundException;
import com.portfolio.exception.InvalidRequestException;

import java.util.List;

public interface ISkillService {
    List<SkillDto> getSkill(Integer id) throws EntityNotFoundException;
    PersonDto addSkill(Integer id, SkillDto skill) throws EntityNotFoundException;
    PersonDto updateSkill(Integer id, Integer skillId, SkillDto skill) throws EntityNotFoundException;
    void deleteSkill(Integer id, Integer skillId) throws EntityNotFoundException, InvalidRequestException;
}
