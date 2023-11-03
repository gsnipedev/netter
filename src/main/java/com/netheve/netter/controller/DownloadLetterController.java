package com.netheve.netter.controller;

import com.netheve.netter.common.helper.FilenameHelper;
import com.netheve.netter.entity.LetterInEntity;
import com.netheve.netter.entity.LetterOutEntity;
import com.netheve.netter.service.FileStorageService;
import com.netheve.netter.service.LetterInService;
import com.netheve.netter.service.LetterOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/download/letter")
public class DownloadLetterController {
    @Autowired
    private LetterInService letterInService;

    @Autowired
    private LetterOutService letterOutService;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/in/{id}")
    private ResponseEntity<ByteArrayResource> downloadLetterIn(@PathVariable Long id){
        LetterInEntity letterIn = letterInService.show(id);
        ByteArrayResource resource = fileStorageService.downloadFile(letterIn.getFileUrl());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,  "attachment; filename=" + letterIn.getFileUrl());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);

    }

    @GetMapping("/out/{id}")
    private ResponseEntity<ByteArrayResource> downloadLetterOut(@PathVariable Long id){
        LetterOutEntity letterOut = letterOutService.show(id);
        ByteArrayResource resource = fileStorageService.downloadFile(letterOut.getFileUrl());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,  "attachment; filename=" + letterOut.getFileUrl());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);

    }
}
