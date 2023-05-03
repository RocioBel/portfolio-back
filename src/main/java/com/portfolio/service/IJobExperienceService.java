package com.portfolio.service;

import com.portfolio.dto.JobExperienceDto;
import com.portfolio.dto.PersonResponseDto;
import com.portfolio.exception.EntityNotFoundException;
import com.portfolio.exception.InvalidRequestException;

import java.util.List;

public interface IJobExperienceService {
    List<JobExperienceDto> getExperiences(Integer id) throws EntityNotFoundException;
    PersonResponseDto addExperience(Integer id, JobExperienceDto work) throws EntityNotFoundException;
    PersonResponseDto updateExperience(Integer id, Integer expId, JobExperienceDto work) throws EntityNotFoundException;
    void deleteExperience(Integer id, Integer expId) throws EntityNotFoundException, InvalidRequestException;
}
