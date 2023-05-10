package com.portfolio.controller;

import com.portfolio.dto.PersonDto;
import com.portfolio.dto.SkillDto;
import com.portfolio.exception.EntityNotFoundException;
import com.portfolio.exception.InvalidRequestException;
import com.portfolio.service.ISkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SkillController {
    @Autowired
    ISkillService skillService;

    @Operation(summary = "Get the skill list of a person")
    @GetMapping("/person/{id}/skill")
    public ResponseEntity<List<SkillDto>> getSkills(@Parameter(description = "id of person")
                                                           @PathVariable Integer id) throws EntityNotFoundException {
        List<SkillDto> skills = skillService.getSkill(id);
        return ResponseEntity.ok(skills);
    }

    @Operation(summary = "Create a new skill")
    @PostMapping("/person/{id}/skill")
    public ResponseEntity<PersonDto> addSkill(@RequestBody SkillDto skill,
                                              @Parameter(description = "id of person")
                                                     @PathVariable Integer id) throws EntityNotFoundException {
        PersonDto person = skillService.addSkill(id, skill);
        return ResponseEntity.ok(person);
    }

    @Operation(summary = "Update a skill")
    @PutMapping("/person/{id}/skill/{skillId}")
    public ResponseEntity<PersonDto> updateSkill(@RequestBody SkillDto skill,
                                                 @Parameter(description = "id of person")
                                                        @PathVariable Integer id,
                                                 @Parameter(description = "id of skill to be updated")
                                                        @PathVariable Integer skillId) throws EntityNotFoundException {
        PersonDto person = skillService.updateSkill(id, skillId, skill);
        return ResponseEntity.ok(person);
    }

    @Operation(summary = "Delete a skill")
    @DeleteMapping("/person/{id}/skill/{skillId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSkill(@Parameter(description = "id of person")
                                @PathVariable Integer id,
                                @Parameter(description = "id of skill to be deleted")
                                @PathVariable Integer skillId) throws EntityNotFoundException, InvalidRequestException {
        skillService.deleteSkill(id, skillId);
    }

}