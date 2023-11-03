package com.netheve.netter.service.impl;

import com.netheve.netter.entity.LetterOutEntity;
import com.netheve.netter.model.letter.out.UploadLetterOutDto;
import com.netheve.netter.repository.LetterOutRepository;
import com.netheve.netter.service.LetterOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LetterOutServiceImpl implements LetterOutService {
    @Autowired
    private LetterOutRepository letterOutRepository;

    @Override
    public List<LetterOutEntity> showAll() {
        return letterOutRepository.findAll();
    }

    @Override
    public LetterOutEntity show(Long id) {
        return letterOutRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Letter not found")
        );
    }

    @Override
    public LetterOutEntity store(UploadLetterOutDto dto) {
        return letterOutRepository.save(
                LetterOutEntity.builder()
                        .agenda(dto.getAgenda())
                        .forDate(dto.getForDate())
                        .sender(dto.getSender())
                        .destination(dto.getDestination())
                        .letterNumber(dto.getLetterNumber())
                        .letterType(dto.getLetterType())
                        .fileUrl(dto.getFileUrl())
                        .description(dto.getDescription())
                        .build()
        );
    }

    @Override
    public LetterOutEntity update() {
        return null;
    }

    @Override
    public void destroy(Long id) {
        letterOutRepository.deleteById(id);
    }
}
