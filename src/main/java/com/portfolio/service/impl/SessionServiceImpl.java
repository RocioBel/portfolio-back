package com.portfolio.service.impl;

import com.portfolio.exception.AccountAlreadyExistsException;
import com.portfolio.model.Role;
import com.portfolio.repository.IAccountRepository;
import com.portfolio.security.JwtService;
import com.portfolio.dto.AuthenticateRequestDto;
import com.portfolio.dto.AccountResponseDTO;
import com.portfolio.model.Account;
import com.portfolio.service.ISessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements ISessionService {
    private final IAccountRepository accountRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public AccountResponseDTO login(AuthenticateRequestDto request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        Account account = accountRepository.findByUsername(request.getUsername()).orElseThrow();

        String token = jwtService.getToken(account);

        return AccountResponseDTO.builder().token(token).build();
    }

    @Override
    public AccountResponseDTO register(AuthenticateRequestDto request) throws AccountAlreadyExistsException {
        Account newUser = Account.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(Role.ADMIN.name())
                .build();

        if (accountRepository.existsByUsername(request.getUsername())) {
            throw new AccountAlreadyExistsException( "El username ya se encuentra en uso");
        }

        Account account = accountRepository.save(newUser);

        String token = jwtService.getToken(account);

        return AccountResponseDTO.builder().token(token).build();
    }

}
