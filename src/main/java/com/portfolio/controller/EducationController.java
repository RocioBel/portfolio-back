package com.portfolio.controller;

import com.portfolio.dto.EducationDto;
import com.portfolio.dto.PersonDto;
import com.portfolio.exception.EntityNotFoundException;
import com.portfolio.exception.InvalidRequestException;
import com.portfolio.service.IEducationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"https://porfolio-web-f29d0.web.app/", "http://localhost:4200"})
public class EducationController {
    @Autowired
    IEducationService educationService;

    @Operation(summary = "Get the education list of a person")
    @GetMapping("/person/{id}/education")
    public ResponseEntity<List<EducationDto>> getEducation(@Parameter(description = "id of person")
                                                           @PathVariable Integer id) throws EntityNotFoundException {
        List<EducationDto> education = educationService.getEducation(id);
        return ResponseEntity.ok(education);
    }

    @Operation(summary = "Create a new education")
    @PostMapping("/person/{id}/education")
    public ResponseEntity<PersonDto> addEducation(@RequestBody EducationDto education,
                                                  @Parameter(description = "id of person")
                                                     @PathVariable Integer id) throws EntityNotFoundException {
        PersonDto person = educationService.addEducation(id, education);
        return ResponseEntity.ok(person);
    }

    @Operation(summary = "Update an education")
    @PutMapping("/person/{id}/education/{edId}")
    public ResponseEntity<PersonDto> updateEducation(@RequestBody EducationDto education,
                                                     @Parameter(description = "id of person")
                                                        @PathVariable Integer id,
                                                     @Parameter(description = "id of education to be updated")
                                                        @PathVariable Integer edId) throws EntityNotFoundException {
        PersonDto person = educationService.updateEducation(id, edId, education);
        return ResponseEntity.ok(person);
    }

    @Operation(summary = "Delete an education")
    @DeleteMapping("/person/{id}/education/{edId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEducation(@Parameter(description = "id of person")
                                @PathVariable Integer id,
                                @Parameter(description = "id of education to be deleted")
                                @PathVariable Integer edId) throws EntityNotFoundException, InvalidRequestException {
        educationService.deleteEducation(id, edId);
    }

}