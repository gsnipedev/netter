package com.netheve.netter.service.impl;

import com.netheve.netter.entity.AccountEntity;
import com.netheve.netter.repository.AccountRepository;
import com.netheve.netter.service.AccountService;
import com.netheve.netter.service.enums.TokenType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountEntity getByToken(String token) {
        return accountRepository.findByAuthToken(token).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Token")
        );
    }

    @Override
    public AccountEntity getByToken(String token, TokenType type) {
        switch (type){
            case NO_SCHEME -> {
                return accountRepository.findByAuthToken(token).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Token")
                );
            }
            case BEARER -> {
                return accountRepository.findByAuthToken(token.replace("Bearer ", "")).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Token")
                );
            }
            case BASIC -> {
                return accountRepository.findByAuthToken(token.replace("Basic ", "")).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Token")
                );
            }
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid token scheme");
        }
    }

    @Override
    public AccountEntity getByUsername(String username) {
        return accountRepository.findByUsername(username).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account with specific username not found")
        );
    }

    @Override
    public List<AccountEntity> getAll() {
        return accountRepository.findAll();
    }
}
