package com.netheve.netter.controlleradvice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class ConstraintViolationControllerAdvice {
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Set<String>> handle(ConstraintViolationException ex){
        final Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

        Map<String, Set<String>> mappedViolations = new HashMap<>();
        if(!constraintViolations.isEmpty()){
            constraintViolations.forEach(data -> mappedViolations.computeIfAbsent(data.getPropertyPath().toString(),
                    (key) -> new HashSet<>()).add(data.getMessage()));
        }

        return mappedViolations;
    }
}
