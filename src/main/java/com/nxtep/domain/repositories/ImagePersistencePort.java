package com.nxtep.domain.repositories;

import com.nxtep.domain.exceptions.ImageProcessingException;

import java.io.File;

public interface ImagePersistencePort {
    String createOneImage(String folder, File file) throws ImageProcessingException;
    void deleteOneImage(String folder, String image) throws ImageProcessingException;
}
