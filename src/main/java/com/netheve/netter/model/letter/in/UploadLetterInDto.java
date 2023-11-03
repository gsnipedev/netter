package com.netheve.netter.model.letter.in;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UploadLetterInDto {
    @NotBlank
    private String agenda;

    @NotBlank
    private String letterNumber;

    @NotBlank
    private String forDate;

    @NotBlank
    private String letterType;

    @NotBlank
    private String sender;

    @NotBlank
    private String issuer;

    @NotBlank
    private String description;

    private String fileUrl;
}
