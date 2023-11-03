package com.netheve.netter.common.helper;

import java.util.Optional;

public class FilenameHelper {
    public static Optional<String> getMimeType(String fileName){
        return Optional.ofNullable(fileName)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(fileName.lastIndexOf(".") + 1));
    }
}
