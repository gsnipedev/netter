package com.netheve.netter.model.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
