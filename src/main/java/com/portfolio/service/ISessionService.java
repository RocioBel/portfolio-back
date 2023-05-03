package com.portfolio.service;


import com.portfolio.exception.AccountAlreadyExistsException;
import com.portfolio.exception.IncorrectLoginException;
import com.portfolio.exception.NotFoundException;
import com.portfolio.dto.AuthenticateRequestDto;
import com.portfolio.dto.AccountResponseDTO;

import java.io.UnsupportedEncodingException;

public interface ISessionService {

    /**
     * Realiza la validación del usuario y contraseña ingresado.
     * En caso de ser correcto, devuelve la cuenta con el token necesario para realizar las demás consultas.
     *
     * @body AccountRequestDto
     * @return
     * @throws NotFoundException
     */
    AccountResponseDTO login(AuthenticateRequestDto request) throws NotFoundException, IncorrectLoginException, UnsupportedEncodingException;
    AccountResponseDTO register(AuthenticateRequestDto request) throws AccountAlreadyExistsException;
}
