package com.portfolio.controller;

import com.portfolio.dto.PersonDto;
import com.portfolio.exception.EntityNotFoundException;
import com.portfolio.service.IPersonService;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDto> getPerson(@PathVariable Integer id) throws EntityNotFoundException {
        PersonDto person = personService.getPerson(id);
        return ResponseEntity.ok(person);
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> getPeople(){
        List<PersonDto> people = personService.getPeople();
        return ResponseEntity.ok(people);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PersonDto> addPerson(@RequestBody PersonDto person){
        PersonDto savedPerson = personService.savePerson(person);
        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable Integer id){
        personService.deletePerson(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<PersonDto> modifyPerson(@RequestBody PersonDto person, @PathVariable Integer id) throws EntityNotFoundException {
        PersonDto update = personService.updatePerson(id, person);
        return ResponseEntity.ok(update);
    }

}