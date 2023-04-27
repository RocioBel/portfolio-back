package com.portfolio.controller;

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
    public ResponseEntity<SkillDto> addSkill(@RequestBody SkillDto skill,
                                                     @Parameter(description = "id of person")
                                                     @PathVariable Integer id) throws EntityNotFoundException {
        SkillDto savedSkill = skillService.addSkill(id, skill);
        return ResponseEntity.ok(savedSkill);
    }

    @Operation(summary = "Update a skill")
    @PutMapping("/person/{id}/skill/{skillId}")
    public ResponseEntity<SkillDto> updateSkill(@RequestBody SkillDto skill,
                                                        @Parameter(description = "id of person")
                                                        @PathVariable Integer id,
                                                        @Parameter(description = "id of skill to be updated")
                                                        @PathVariable Integer skillId) throws EntityNotFoundException {
        SkillDto updatedSkill = skillService.updateSkill(id, skillId, skill);
        return ResponseEntity.ok(updatedSkill);
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