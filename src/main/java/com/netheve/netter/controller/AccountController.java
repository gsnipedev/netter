package com.netheve.netter.controller;

import com.netheve.netter.entity.AccountEntity;
import com.netheve.netter.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/all")
    public ResponseEntity<List<AccountEntity>> getAllAccount(){
        return ResponseEntity.ok(accountService.getAll());
    }

    @GetMapping
    public ResponseEntity<AccountEntity> getOne(@RequestHeader("Authorization") String bearerToken){
        return ResponseEntity.ok(accountService.getByToken(bearerToken.replace("Bearer ", "")));
    }
}
