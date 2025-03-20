package com.nxtep.domain.services;

import java.io.File;

public interface ImageGeneratorPort {
    File createImageFromAnotherImage(String imageUrl);
}
