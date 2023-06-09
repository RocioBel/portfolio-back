package com.portfolio.controller;

import com.portfolio.dto.JobExperienceDto;
import com.portfolio.dto.PersonDto;
import com.portfolio.exception.EntityNotFoundException;
import com.portfolio.exception.InvalidRequestException;
import com.portfolio.service.IJobExperienceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"https://porfolio-web-f29d0.web.app/", "http://localhost:4200"})
public class JobExperienceController {
    @Autowired
    IJobExperienceService jobExperienceService;

    @Operation(summary = "Get the job experience list of a person")
    @GetMapping("/person/{id}/experience")
    public ResponseEntity<List<JobExperienceDto>> getExperiences(@Parameter(description = "id of person")
                                                                     @PathVariable Integer id) throws EntityNotFoundException {
        List<JobExperienceDto> experiences = jobExperienceService.getExperiences(id);
        return ResponseEntity.ok(experiences);
    }

    @Operation(summary = "Create a new job experience")
    @PostMapping("/person/{id}/experience")
    public ResponseEntity<PersonDto> addExperience(@RequestBody JobExperienceDto experience,
                                                   @Parameter(description = "id of person")
                                                          @PathVariable Integer id) throws EntityNotFoundException {
        PersonDto person = jobExperienceService.addExperience(id, experience);
        return ResponseEntity.ok(person);
    }

    @Operation(summary = "Update a job experience")
    @PutMapping("/person/{id}/experience/{jobId}")
    public ResponseEntity<PersonDto> updateExperience(@RequestBody JobExperienceDto experience,
                                                      @Parameter(description = "id of person")
                                                             @PathVariable Integer id,
                                                      @Parameter(description = "id of job experience to be updated")
                                                             @PathVariable Integer jobId) throws EntityNotFoundException {
        PersonDto person = jobExperienceService.updateExperience(id, jobId, experience);
        return ResponseEntity.ok(person);
    }

    @Operation(summary = "Delete a job experience")
    @DeleteMapping("/person/{id}/experience/{expId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteExperience(@Parameter(description = "id of person")
                                 @PathVariable Integer id,
                                 @Parameter(description = "id of job experience to be deleted")
                                 @PathVariable Integer expId) throws EntityNotFoundException, InvalidRequestException {
        jobExperienceService.deleteExperience(id, expId);
    }

}