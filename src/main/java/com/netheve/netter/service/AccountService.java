package com.netheve.netter.service;

import com.netheve.netter.entity.AccountEntity;
import com.netheve.netter.service.enums.TokenType;

import java.util.List;

public interface AccountService {
    AccountEntity getByToken(String token);
    AccountEntity getByToken(String token, TokenType type);
    AccountEntity getByUsername(String username);
    List<AccountEntity> getAll();
}
