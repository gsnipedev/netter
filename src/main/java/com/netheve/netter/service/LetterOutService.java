package com.netheve.netter.service;

import com.netheve.netter.entity.LetterOutEntity;
import com.netheve.netter.model.letter.out.UploadLetterOutDto;
import org.springframework.core.io.ByteArrayResource;

import java.util.List;

public interface LetterOutService {
    List<LetterOutEntity> showAll();
    LetterOutEntity show(Long id);
    LetterOutEntity store(UploadLetterOutDto dto);
    LetterOutEntity update();
    void destroy(Long id);
}
