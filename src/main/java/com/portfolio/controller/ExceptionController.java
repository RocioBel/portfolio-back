package com.portfolio.controller;

import com.portfolio.exception.EntityAlreadyExistsException;
import com.portfolio.exception.EntityNotFoundException;
import com.portfolio.dto.ErrorDto;
import com.portfolio.exception.InvalidRequestException;
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

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ErrorDto> handleEntityAlreadyExists(EntityAlreadyExistsException e){
        ErrorDto error = ErrorDto.builder().code(HttpStatus.BAD_REQUEST.value()).description(e.getMessage()).build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorDto> handleInvalidRequestException(InvalidRequestException e){
        ErrorDto error = ErrorDto.builder().code(HttpStatus.BAD_REQUEST.value()).description(e.getMessage()).build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(EntityAlreadyExistsException e){
        ErrorDto error = ErrorDto.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).description(e.getMessage()).build();
        return ResponseEntity.badRequest().body(error);
    }
}
