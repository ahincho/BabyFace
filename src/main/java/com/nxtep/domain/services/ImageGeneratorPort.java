package com.nxtep.domain.services;

import com.nxtep.domain.exceptions.ImageProcessingException;

import java.io.File;

public interface ImageGeneratorPort {
    File createImageFromAnotherImage(String username, String imageUrl) throws ImageProcessingException;
}
