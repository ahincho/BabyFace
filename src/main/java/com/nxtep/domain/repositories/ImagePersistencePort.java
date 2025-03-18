package com.nxtep.domain.repositories;

import java.io.File;

public interface ImagePersistencePort {
    String createOneImage(String folder, File file);
    void deleteOneImage(String folder, String image);
}
