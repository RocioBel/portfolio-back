package com.portfolio.controller;

import com.portfolio.dto.JobExperienceDto;
import com.portfolio.exception.EntityNotFoundException;
import com.portfolio.exception.InvalidRequestException;
import com.portfolio.service.IJobExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobExperienceController {
    @Autowired
    IJobExperienceService jobExperienceService;

    @GetMapping("/person/{id}/experience")
    public ResponseEntity<List<JobExperienceDto>> getExperiences(@PathVariable Integer id) throws EntityNotFoundException {
        List<JobExperienceDto> experiences = jobExperienceService.getExperiences(id);
        return ResponseEntity.ok(experiences);
    }

    @PostMapping("/person/{id}/experience")
    public ResponseEntity<JobExperienceDto> addExperience(@RequestBody JobExperienceDto experience, @PathVariable Integer id) throws EntityNotFoundException {
        JobExperienceDto savedExperience = jobExperienceService.addExperience(id, experience);
        return ResponseEntity.ok(savedExperience);
    }

    @PutMapping("/person/{id}/experience/{jobId}")
    public ResponseEntity<JobExperienceDto> updateExperience(@RequestBody JobExperienceDto experience, @PathVariable Integer id,
                                                                  @PathVariable Integer jobId) throws EntityNotFoundException {
        JobExperienceDto updatedExperience = jobExperienceService.updateExperience(id, jobId, experience);
        return ResponseEntity.ok(updatedExperience);
    }

    @DeleteMapping("/person/{id}/experience/{expId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteExperience(@PathVariable Integer id, @PathVariable Integer expId) throws EntityNotFoundException, InvalidRequestException {
        jobExperienceService.deleteExperience(id, expId);
    }

}