package com.portfolio.service;

import com.portfolio.dto.JobExperienceRequest;
import com.portfolio.dto.JobExperienceResponse;
import com.portfolio.exception.EntityNotFoundException;

import java.util.List;

public interface IJobExperienceService {
    List<JobExperienceResponse> getExperiences(Integer id) throws EntityNotFoundException;
    JobExperienceResponse addExperience(Integer id, JobExperienceRequest work) throws EntityNotFoundException;
    JobExperienceResponse updateExperience(Integer id, Integer expId, JobExperienceRequest work) throws EntityNotFoundException;
    void deleteExperience(Integer id, Integer expId) throws EntityNotFoundException;
}
