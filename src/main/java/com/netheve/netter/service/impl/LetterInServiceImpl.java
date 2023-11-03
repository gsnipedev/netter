package com.netheve.netter.service.impl;

import com.netheve.netter.entity.LetterInEntity;
import com.netheve.netter.model.letter.in.UploadLetterInDto;
import com.netheve.netter.repository.LetterInRepository;
import com.netheve.netter.service.LetterInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LetterInServiceImpl implements LetterInService {
    @Autowired
    private LetterInRepository letterInRepository;

    @Override
    public List<LetterInEntity> showAll() {
        return letterInRepository.findAll();
    }

    @Override
    public LetterInEntity show(Long id) {
        return letterInRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Letter not found")
        );
    }

    @Override
    public LetterInEntity store(UploadLetterInDto dto) {
        return letterInRepository.save(
                LetterInEntity.builder()
                        .agenda(dto.getAgenda())
                        .forDate(dto.getForDate())
                        .issuer(dto.getIssuer())
                        .sender(dto.getSender())
                        .letterNumber(dto.getLetterNumber())
                        .letterType(dto.getLetterType())
                        .fileUrl(dto.getFileUrl())
                        .description(dto.getDescription())
                        .build()
        );
    }

    @Override
    public LetterInEntity update() {
        return null;
    }

    @Override
    public void destroy(Long id) {
        letterInRepository.deleteById(id);
    }
}
