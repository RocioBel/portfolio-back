package com.portfolio.service;


import com.portfolio.exception.AccountAlreadyExistsException;
import com.portfolio.exception.IncorrectLoginException;
import com.portfolio.exception.NotFoundException;
import com.portfolio.dto.AuthenticateRequestDto;
import com.portfolio.dto.AccountResponseDTO;

import java.io.UnsupportedEncodingException;

public interface ISessionService {
    AccountResponseDTO login(AuthenticateRequestDto request) throws NotFoundException, IncorrectLoginException, UnsupportedEncodingException;
    AccountResponseDTO register(AuthenticateRequestDto request) throws AccountAlreadyExistsException;
}
