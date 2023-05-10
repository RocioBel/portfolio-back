package com.portfolio.controller;

import com.portfolio.dto.LanguageDto;
import com.portfolio.dto.PersonDto;
import com.portfolio.exception.EntityNotFoundException;
import com.portfolio.exception.InvalidRequestException;
import com.portfolio.service.ILanguageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LanguageController {
    @Autowired
    ILanguageService languageService;

    @Operation(summary = "Get the language list of a person")
    @GetMapping("/person/{id}/language")
    public ResponseEntity<List<LanguageDto>> getLanguages(@Parameter(description = "id of person")
                                                           @PathVariable Integer id) throws EntityNotFoundException {
        List<LanguageDto> languages = languageService.getLanguage(id);
        return ResponseEntity.ok(languages);
    }

    @Operation(summary = "Create a new language")
    @PostMapping("/person/{id}/language")
    public ResponseEntity<PersonDto> addLanguage(@RequestBody LanguageDto language,
                                                 @Parameter(description = "id of person")
                                                     @PathVariable Integer id) throws EntityNotFoundException {
        PersonDto person = languageService.addLanguage(id, language);
        return ResponseEntity.ok(person);
    }

    @Operation(summary = "Update a language")
    @PutMapping("/person/{id}/language/{langId}")
    public ResponseEntity<PersonDto> updateLanguage(@RequestBody LanguageDto language,
                                                    @Parameter(description = "id of person")
                                                        @PathVariable Integer id,
                                                    @Parameter(description = "id of language to be updated")
                                                        @PathVariable Integer langId) throws EntityNotFoundException {
        PersonDto person = languageService.updateLanguage(id, langId, language);
        return ResponseEntity.ok(person);
    }

    @Operation(summary = "Delete a language")
    @DeleteMapping("/person/{id}/language/{langId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLanguage(@Parameter(description = "id of person")
                                @PathVariable Integer id,
                                @Parameter(description = "id of language to be deleted")
                                @PathVariable Integer langId) throws EntityNotFoundException, InvalidRequestException {
        languageService.deleteLanguage(id, langId);
    }

}