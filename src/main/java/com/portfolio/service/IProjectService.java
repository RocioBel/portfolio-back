package com.portfolio.service;

import com.portfolio.dto.PersonDto;
import com.portfolio.dto.ProjectDto;
import com.portfolio.exception.EntityNotFoundException;
import com.portfolio.exception.InvalidRequestException;

import java.util.List;

public interface IProjectService {
    List<ProjectDto> getProjects(Integer id) throws EntityNotFoundException;
    PersonDto addProject(Integer id, ProjectDto project) throws EntityNotFoundException;
    PersonDto updateProject(Integer id, Integer expId, ProjectDto project) throws EntityNotFoundException;
    void deleteProject(Integer id, Integer projectId) throws EntityNotFoundException, InvalidRequestException;
}
