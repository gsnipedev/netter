package com.netheve.netter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netheve.netter.entity.LetterOutEntity;
import com.netheve.netter.model.letter.out.UploadLetterOutDto;
import com.netheve.netter.service.FileStorageService;
import com.netheve.netter.service.LetterOutService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/letter/out")
public class LetterOutController {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LetterOutService letterOutService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private Validator validator;

    @GetMapping
    private ResponseEntity<List<LetterOutEntity>> getAll(){
        return ResponseEntity.ok(letterOutService.showAll());
    }

    @PostMapping
    private ResponseEntity<LetterOutEntity> uploadLetterOut(
            @RequestParam("json") String json,
            @RequestParam("letter") MultipartFile letter){
        UploadLetterOutDto dto;

        try{
            dto = objectMapper.readValue(json, UploadLetterOutDto.class);
        }catch (JsonProcessingException jsonEx){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid json was given");
        }catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request");
        }

        final Set<ConstraintViolation<UploadLetterOutDto>> violations = validator.validate(dto);
        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }

        String fileUrl = fileStorageService.storeFile(letter);
        dto.setFileUrl(fileUrl);

        LetterOutEntity letterOutEntity = letterOutService.store(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(letterOutEntity);
    }

}
