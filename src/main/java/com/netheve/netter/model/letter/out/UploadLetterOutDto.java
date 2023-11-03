package com.netheve.netter.model.letter.out;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UploadLetterOutDto {
    @NotBlank
    private String agenda;

    @NotBlank
    private String letterNumber;

    @NotNull
    private LocalDateTime forDate;

    @NotBlank
    private String letterType;

    @NotBlank
    private String sender;

    @NotBlank
    private String destination;

    @NotBlank
    private String description;

    private String fileUrl;
}
