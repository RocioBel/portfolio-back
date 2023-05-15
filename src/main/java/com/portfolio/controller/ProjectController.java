package com.portfolio.controller;

import com.portfolio.dto.PersonDto;
import com.portfolio.dto.ProjectDto;
import com.portfolio.exception.EntityNotFoundException;
import com.portfolio.exception.InvalidRequestException;
import com.portfolio.service.IProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"https://porfolio-web-f29d0.web.app/", "http://localhost:4200"})
public class ProjectController {
    @Autowired
    IProjectService projectService;

    @Operation(summary = "Get the project list of a person")
    @GetMapping("/person/{id}/project")
    public ResponseEntity<List<ProjectDto>> getProjects(@Parameter(description = "id of person")
                                                        @PathVariable Integer id) throws EntityNotFoundException {
        List<ProjectDto> projects = projectService.getProjects(id);
        return ResponseEntity.ok(projects);
    }

    @Operation(summary = "Create a new project")
    @PostMapping("/person/{id}/project")
    public ResponseEntity<PersonDto> addProject(@RequestBody ProjectDto project,
                                                @Parameter(description = "id of person")
                                                 @PathVariable Integer id) throws EntityNotFoundException {
        PersonDto person = projectService.addProject(id, project);
        return ResponseEntity.ok(person);
    }

    @Operation(summary = "Update a project")
    @PutMapping("/person/{id}/project/{projectId}")
    public ResponseEntity<PersonDto> updateProject(@RequestBody ProjectDto project,
                                                   @Parameter(description = "id of person")
                                                    @PathVariable Integer id,
                                                   @Parameter(description = "id of project to be updated")
                                                    @PathVariable Integer projectId) throws EntityNotFoundException {
        PersonDto person = projectService.updateProject(id, projectId, project);
        return ResponseEntity.ok(person);
    }

    @Operation(summary = "Delete a project")
    @DeleteMapping("/person/{id}/project/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProject(@Parameter(description = "id of person")
                              @PathVariable Integer id,
                              @Parameter(description = "id of project to be deleted")
                              @PathVariable Integer projectId) throws EntityNotFoundException, InvalidRequestException {
        projectService.deleteProject(id, projectId);
    }

}