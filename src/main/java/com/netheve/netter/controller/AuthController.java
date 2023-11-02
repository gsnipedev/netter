package com.netheve.netter.controller;

import com.netheve.netter.model.auth.LoginRequest;
import com.netheve.netter.model.auth.LoginResponse;
import com.netheve.netter.model.account.CreateAccountDto;
import com.netheve.netter.model.violation.ConstraintViolationResponse;
import com.netheve.netter.service.AuthService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    
    @Autowired
    private Validator validator;

    @PostMapping("/login")
    private ResponseEntity<LoginResponse> performLogin(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }
    
    @PostMapping("/register")
    private ResponseEntity<?> createAccount(@RequestBody CreateAccountDto request){
        if(!request.getPassword().equals(request.getPasswordRepeat())){
            Map<String, Set<String>> mappedViolations = new HashMap<>();
            Set<String> violations = new HashSet<>();
            violations.add("Password repeat does not match with password");
            mappedViolations.put("passwordRepeat", violations);

            return ResponseEntity.badRequest().body(new ConstraintViolationResponse<>(mappedViolations));
        }

        Set<ConstraintViolation<CreateAccountDto>> constraintViolations = validator.validate(request);
        if(!constraintViolations.isEmpty())
            throw new ConstraintViolationException("Bad Request", constraintViolations);

        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }
}
