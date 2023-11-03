package com.netheve.netter.service;

import com.netheve.netter.entity.LetterInEntity;
import com.netheve.netter.model.letter.in.UploadLetterInDto;
import org.springframework.core.io.ByteArrayResource;

import java.util.List;

public interface LetterInService {
    List<LetterInEntity> showAll();
    LetterInEntity show(Long id);
    LetterInEntity store(UploadLetterInDto dto);
    LetterInEntity update();
    void destroy(Long id);
}
