package com.portfolio.controller;

import com.portfolio.exception.EntityNotFoundException;
import com.portfolio.model.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleEntityNotFound(EntityNotFoundException e){
        ErrorDto error = ErrorDto.builder().code(HttpStatus.NOT_FOUND.value()).description(e.getMessage()).build();
        return ResponseEntity.badRequest().body(error);
    }
}
