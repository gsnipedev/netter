package com.netheve.netter.model.letter.out;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UploadLetterOutDto {
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
    private String destination;

    @NotBlank
    private String description;

    private String fileUrl;
}
