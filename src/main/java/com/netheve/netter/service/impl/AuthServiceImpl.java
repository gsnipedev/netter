package com.netheve.netter.service.impl;

import com.netheve.netter.common.crypto.BCrypt;
import com.netheve.netter.common.util.EpochTimeHelper;
import com.netheve.netter.entity.AccountEntity;
import com.netheve.netter.entity.enums.AccountRank;
import com.netheve.netter.model.auth.LoginRequest;
import com.netheve.netter.model.auth.LoginResponse;
import com.netheve.netter.model.account.CreateAccountDto;
import com.netheve.netter.repository.AccountRepository;
import com.netheve.netter.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public LoginResponse login(LoginRequest request){
        AccountEntity account = accountRepository.findByUsername(request.getUsername()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong username or password")
        );

        if(!BCrypt.checkpw(request.getPassword(), account.getPassword()))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong username or password");

        account.setAuthToken(UUID.randomUUID().toString());
        account.setTokenExpiredAt(EpochTimeHelper.nextDay());
        accountRepository.save(account);

        return new LoginResponse(account.getAuthToken());
    }

    @Override
    public boolean validateToken(String authToken){
        AccountEntity account = accountRepository.findByAuthToken(authToken).orElse(null);
        if(account == null)
            return false;

        return account.getTokenExpiredAt() >= EpochTimeHelper.now();
    }

    @Override
    public boolean validTokenAs(String authToken, AccountRank rank) {
        AccountEntity account = accountRepository.findByAuthToken(authToken).orElse(null);
        if(account == null || account.getRank() != rank)
            return false;

        return account.getTokenExpiredAt() >= EpochTimeHelper.now();
    }

    @Override
    public AccountEntity register(CreateAccountDto request) {
        if(accountRepository.existsByUsername(request.getUsername()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already taken");

        AccountEntity account = AccountEntity.builder()
                .username(request.getUsername())
                .password(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .rank(AccountRank.STAFF)
                .role(request.getRole())
                .createdAt(LocalDateTime.now())
                .build();

        return accountRepository.save(account);
    }
}
