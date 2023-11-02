package com.netheve.netter.common.util;

import jakarta.validation.ConstraintViolation;

import java.util.Set;

public class ViolationsMessageExtractor<T> {

    public static <T> String[] extractMessages(Set<ConstraintViolation<T>> violations)
    {
        String[] result = new String[violations.size()];

        int i = 0;
        for(ConstraintViolation<T> violation : violations)
        {
            result[i] = violation.getMessage();
            i++;
        }

        return result;
    }

}
