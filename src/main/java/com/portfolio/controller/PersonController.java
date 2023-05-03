package com.portfolio.controller;

import com.portfolio.dto.PersonRequestDto;
import com.portfolio.dto.PersonResponseDto;
import com.portfolio.exception.EntityNotFoundException;
import com.portfolio.service.IPersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    IPersonService personService;

    @Operation(summary = "Get a person by its id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonResponseDto> getPerson(@Parameter(description = "id of person to be searched")
                                                   @PathVariable Integer id) throws EntityNotFoundException {
        PersonResponseDto person = personService.getPerson(id);
        return ResponseEntity.ok(person);
    }

    @Operation(summary = "Get the people list")
    @GetMapping
    public ResponseEntity<List<PersonResponseDto>> getPeople(){
        List<PersonResponseDto> people = personService.getPeople();
        return ResponseEntity.ok(people);
    }

    @Operation(summary = "Create a new person")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PersonResponseDto> addPerson(@RequestBody PersonRequestDto person){
        PersonResponseDto savedPerson = personService.savePerson(person);
        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);

    }

    @Operation(summary = "Delete a person")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@Parameter(description = "id of person to be deleted")
                                 @PathVariable Integer id){
        personService.deletePerson(id);
    }

    @Operation(summary = "Update a person")
    @PutMapping("/{id}")
    public ResponseEntity<PersonResponseDto> modifyPerson(@RequestBody PersonRequestDto person,
                                                          @Parameter(description = "id of person to be updated")
                                                  @PathVariable Integer id) throws EntityNotFoundException {
        PersonResponseDto update = personService.updatePerson(id, person);
        return ResponseEntity.ok(update);
    }

}