package com.portfolio.controller;

import com.portfolio.exception.AccountAlreadyExistsException;
import com.portfolio.exception.IncorrectLoginException;
import com.portfolio.service.ISessionService;
import com.portfolio.exception.NotFoundException;
import com.portfolio.dto.AuthenticateRequestDto;
import com.portfolio.dto.AccountResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@CrossOrigin(origins = {"https://porfolio-web-f29d0.web.app/"})
public class SessionController {
    @Autowired
    ISessionService service;

    @PostMapping("/signin")
    public AccountResponseDTO login(@RequestBody AuthenticateRequestDto request) throws NotFoundException, IncorrectLoginException, UnsupportedEncodingException {
        return service.login(request);
    }
    @PostMapping("/register")
    public AccountResponseDTO register(@RequestBody AuthenticateRequestDto request) throws NotFoundException, AccountAlreadyExistsException {
        return service.register(request);
    }

}
