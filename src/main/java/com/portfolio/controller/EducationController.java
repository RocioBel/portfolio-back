package com.portfolio.controller;

import com.portfolio.dto.EducationDto;
import com.portfolio.exception.EntityNotFoundException;
import com.portfolio.exception.InvalidRequestException;
import com.portfolio.service.IEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EducationController {
    @Autowired
    IEducationService educationService;

    @GetMapping("/person/{id}/education")
    public ResponseEntity<List<EducationDto>> getEducation(@PathVariable Integer id) throws EntityNotFoundException {
        List<EducationDto> education = educationService.getEducation(id);
        return ResponseEntity.ok(education);
    }

    @PostMapping("/person/{id}/education")
    public ResponseEntity<EducationDto> addEducation(@RequestBody EducationDto education, @PathVariable Integer id) throws EntityNotFoundException {
        EducationDto savedEducation = educationService.addEducation(id, education);
        return ResponseEntity.ok(savedEducation);
    }

    @PutMapping("/person/{id}/education/{edId}")
    public ResponseEntity<EducationDto> updateEducation(@RequestBody EducationDto education, @PathVariable Integer id, @PathVariable Integer edId) throws EntityNotFoundException {
        EducationDto updatedEducation = educationService.updateEducation(id, edId, education);
        return ResponseEntity.ok(updatedEducation);
    }

    @DeleteMapping("/person/{id}/education/{edId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEducation(@PathVariable Integer id, @PathVariable Integer edId) throws EntityNotFoundException, InvalidRequestException {
        educationService.deleteEducation(id, edId);
    }

}