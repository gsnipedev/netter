package com.netheve.netter.service.impl;

import com.netheve.netter.common.configuration.property.FileStorageProperty;
import com.netheve.netter.common.util.EpochTimeHelper;
import com.netheve.netter.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    private final Path fileStorageLocation;

    @Autowired
    public FileStorageServiceImpl(FileStorageProperty fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Couldn't create the directory where the upload files will be saved.", ex);
        }
    }
    public String storeFile(MultipartFile file) {
        // Normalize file name
        String originalFileName = file.getOriginalFilename();
        if(originalFileName == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File name cannot be null");
        }
        String fileName = EpochTimeHelper.now() + "-" + StringUtils.cleanPath(originalFileName);
        try {
            // Check if the file's name contains valid  characters or not
            if (fileName.contains("..")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sorry! File name which contains invalid path sequence " + fileName);
            }
            // Copy file to the target place (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;

        } catch (IOException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "IO Exception was thrown");
        }
    }

    @Override
    public ByteArrayResource downloadFile(String filePath){
        try{
            Path target = this.fileStorageLocation.resolve(filePath);
            return new ByteArrayResource(Files.readAllBytes(target));
        }catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Download Failed");
        }
    }
}
