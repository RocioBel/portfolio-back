package com.portfolio.controller;

import com.portfolio.dto.JobExperienceRequest;
import com.portfolio.dto.JobExperienceResponse;
import com.portfolio.exception.EntityNotFoundException;
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
    public ResponseEntity<List<JobExperienceResponse>> getExperiences(@PathVariable Integer id) throws EntityNotFoundException {
        List<JobExperienceResponse> experiences = jobExperienceService.getExperiences(id);
        return ResponseEntity.ok(experiences);
    }

    @PostMapping("/person/{id}/experience/new")
    public ResponseEntity<JobExperienceResponse> addExperience(@RequestBody JobExperienceRequest experience, @PathVariable Integer id) throws EntityNotFoundException {
        JobExperienceResponse savedExperience = jobExperienceService.addExperience(id, experience);
        return ResponseEntity.ok(savedExperience);
    }

    @PutMapping("/person/{id}/experience/{jobId}/update")
    public ResponseEntity<JobExperienceResponse> updateExperience(@RequestBody JobExperienceRequest experience, @PathVariable Integer id,
                                                                 @PathVariable Integer expId) throws EntityNotFoundException {
        JobExperienceResponse updatedExperience = jobExperienceService.updateExperience(id, expId, experience);
        return ResponseEntity.ok(updatedExperience);
    }

    @DeleteMapping("/person/{id}/experience/{expId}/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteExperience(@PathVariable Integer id, @PathVariable Integer expId) throws EntityNotFoundException {
        jobExperienceService.deleteExperience(id, expId);
    }

}