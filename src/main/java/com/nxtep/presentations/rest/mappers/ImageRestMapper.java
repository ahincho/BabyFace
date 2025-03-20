package com.nxtep.presentations.rest.mappers;

import com.nxtep.domain.exceptions.ImageConversionException;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class ImageRestMapper {
    private ImageRestMapper() {}
    public static File multipartToFile(MultipartFile multipartFile) throws ImageConversionException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(multipartFile.getBytes());
        } catch (IOException e) {
            throw new ImageConversionException("Could not convert multipart into a file");
        }
        return file;
    }
}
