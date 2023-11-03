package com.netheve.netter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netheve.netter.entity.LetterInEntity;
import com.netheve.netter.model.letter.in.UploadLetterInDto;
import com.netheve.netter.service.FileStorageService;
import com.netheve.netter.service.LetterInService;
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
@RequestMapping("/letter/in")
public class LetterInController {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LetterInService letterInService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private Validator validator;

    @GetMapping
    private ResponseEntity<List<LetterInEntity>> getAll(){
        return ResponseEntity.ok(letterInService.showAll());
    }

    @PostMapping("/in/upload")
    private ResponseEntity<LetterInEntity> uploadLetterIn(
            @RequestParam("json") String json,
            @RequestParam("letter") MultipartFile letter){
        UploadLetterInDto dto;

        try{
            dto = objectMapper.readValue(json, UploadLetterInDto.class);
        }catch (JsonProcessingException jsonEx){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid json was given");
        }catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request");
        }

        final Set<ConstraintViolation<UploadLetterInDto>> violations = validator.validate(dto);
        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }

        String fileUrl = fileStorageService.storeFile(letter);
        dto.setFileUrl(fileUrl);

        LetterInEntity letterInEntity = letterInService.store(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(letterInEntity);

    }
}
