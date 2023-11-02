package com.netheve.netter.service;

import com.netheve.netter.entity.AccountEntity;
import com.netheve.netter.entity.enums.AccountRank;
import com.netheve.netter.model.auth.LoginRequest;
import com.netheve.netter.model.auth.LoginResponse;
import com.netheve.netter.model.account.CreateAccountDto;
import com.netheve.netter.entity.AccountEntity;

public interface AuthService {
    LoginResponse login(LoginRequest request);
    boolean validateToken(String authToken);

    boolean validTokenAs(String authToken, AccountRank rank);
    AccountEntity register(CreateAccountDto request);
}
