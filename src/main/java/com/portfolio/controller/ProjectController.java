package com.portfolio.controller;

import com.portfolio.dto.ProjectDto;
import com.portfolio.exception.EntityNotFoundException;
import com.portfolio.exception.InvalidRequestException;
import com.portfolio.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {
    @Autowired
    IProjectService projectService;

    @GetMapping("/person/{id}/project")
    public ResponseEntity<List<ProjectDto>> getProjects(@PathVariable Integer id) throws EntityNotFoundException {
        List<ProjectDto> projects = projectService.getProjects(id);
        return ResponseEntity.ok(projects);
    }

    @PostMapping("/person/{id}/project")
    public ResponseEntity<ProjectDto> addProject(@RequestBody ProjectDto project, @PathVariable Integer id) throws EntityNotFoundException {
        ProjectDto savedProject = projectService.addProject(id, project);
        return ResponseEntity.ok(savedProject);
    }

    @PutMapping("/person/{id}/project/{projectId}")
    public ResponseEntity<ProjectDto> updateProject(@RequestBody ProjectDto project, @PathVariable Integer id, @PathVariable Integer projectId) throws EntityNotFoundException {
        ProjectDto updatedProject = projectService.updateProject(id, projectId, project);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/person/{id}/project/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProject(@PathVariable Integer id, @PathVariable Integer projectId) throws EntityNotFoundException, InvalidRequestException {
        projectService.deleteProject(id, projectId);
    }

}