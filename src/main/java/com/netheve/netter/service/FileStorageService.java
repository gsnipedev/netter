package com.netheve.netter.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String storeFile(MultipartFile file);
    ByteArrayResource downloadFile(String filePath);
}
