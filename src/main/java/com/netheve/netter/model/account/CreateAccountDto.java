package com.netheve.netter.model.account;

import com.netheve.netter.entity.enums.AccountRole;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateAccountDto {
    @Size(min = 8, max = 16, message = "Username must be between 8 to 16 characters")
    private String username;

    @Size(min = 8, max = 255, message = "Password must be between 8 to 255 characters")
    private String password;
    private String passwordRepeat;

    @Size(min = 3, max = 32, message = "First name must be between 3 to 32 characters")
    private String firstName;

    @Size(min = 3, max = 32, message = "First name must be between 3 to 32 characters")
    private String lastName;

    @NotNull
    private AccountRole role;
}
