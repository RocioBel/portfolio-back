package com.portfolio.controller;

import com.portfolio.exception.*;
import com.portfolio.dto.ErrorDto;
import io.jsonwebtoken.ExpiredJwtException;
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

    @ExceptionHandler(IncorrectLoginException.class)
    public ResponseEntity<ErrorDto> handleIncorrectLoginException(IncorrectLoginException e){
        ErrorDto error = ErrorDto.builder().code(HttpStatus.BAD_REQUEST.value()).description(e.getMessage()).build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(AccountAlreadyExistsException.class)
    public ResponseEntity<ErrorDto> handleAccountAlreadyExistsException(AccountAlreadyExistsException e){
        ErrorDto error = ErrorDto.builder().code(HttpStatus.BAD_REQUEST.value()).description(e.getMessage()).build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorDto> handleExpiredJwtException(ExpiredJwtException e){
        ErrorDto error = ErrorDto.builder().code(HttpStatus.BAD_REQUEST.value()).description(e.getMessage()).build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(Exception e){
        ErrorDto error = ErrorDto.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).description(e.getMessage()).build();
        return ResponseEntity.badRequest().body(error);
    }
}
