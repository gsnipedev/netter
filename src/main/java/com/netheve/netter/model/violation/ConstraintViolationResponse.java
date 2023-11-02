package com.netheve.netter.model.violation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConstraintViolationResponse<T> {
    private Map<String, T> violations;
}
