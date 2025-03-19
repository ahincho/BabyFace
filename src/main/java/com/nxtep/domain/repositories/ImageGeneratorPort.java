package com.nxtep.domain.repositories;

import java.io.File;
import java.io.IOException;

public interface ImageGeneratorPort {
    File createImageFromAnotherImage(File imageFile) throws IOException;
}
