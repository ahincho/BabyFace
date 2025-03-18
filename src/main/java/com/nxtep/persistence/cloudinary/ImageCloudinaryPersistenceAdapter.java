package com.nxtep.persistence.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import com.nxtep.domain.repositories.ImagePersistencePort;

import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ImageCloudinaryPersistenceAdapter implements ImagePersistencePort {
    private final Cloudinary cloudinary;
    public ImageCloudinaryPersistenceAdapter(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }
    @Override
    public String createOneImage(String folder, File file) {
        try {
            Map<String, Object> uploadOptions = new HashMap<>();
            uploadOptions.put("folder", folder);
            Map result = this.cloudinary.uploader().upload(file, uploadOptions);
            if (!Files.deleteIfExists(file.toPath())) {
                throw new IOException("Could not delete temporary file: " + file.getAbsolutePath());
            }
            return (String) result.get("secure_url");
        } catch (Exception exception) {
            throw new RuntimeException("Error uploading image to Cloudinary: " + exception.getMessage(), exception);
        }
    }
    @Override
    public void deleteOneImage(String folder, String image) {
        try {
            String publicId = extractPublicId(folder, image);
            Map result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            if (!"ok".equals(result.get("result"))) {
                throw new RuntimeException("Failed to delete image from Cloudinary");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error deleting image: " + e.getMessage(), e);
        }
    }
    private String extractPublicId(String imageUrl, String folderPath) {
        String filename = imageUrl.substring(imageUrl.lastIndexOf("/") + 1, imageUrl.lastIndexOf("."));
        return folderPath + "/" + filename;
    }
}
