package com.portfolio.controller;

import com.portfolio.dto.PersonRequest;
import com.portfolio.dto.PersonResponse;
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
    public ResponseEntity<PersonResponse> getPerson(@PathVariable Integer id) throws EntityNotFoundException {
        PersonResponse person = personService.getPerson(id);
        return ResponseEntity.ok(person);
    }

    @GetMapping
    public ResponseEntity<List<PersonResponse>> getPeople(){
        List<PersonResponse> people = personService.getPeople();
        return ResponseEntity.ok(people);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PersonResponse> addPerson(@RequestBody PersonRequest person){
        PersonResponse savedPerson = personService.savePerson(person);
        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable Integer id){
        personService.deletePerson(id);
    }

    @PostMapping("/{id}/modify")
    public ResponseEntity<PersonResponse> modifyPerson(@RequestBody PersonRequest person, @PathVariable Integer id) throws EntityNotFoundException {
        PersonResponse update = personService.updatePerson(id, person);
        return ResponseEntity.ok(update);
    }

}