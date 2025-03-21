package com.nxtep.infrastructure.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import com.nxtep.domain.exceptions.ImageProcessingException;
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
    private final String cloudinaryEnvironment;
    public ImageCloudinaryPersistenceAdapter(Cloudinary cloudinary, String cloudinaryEnvironment) {
        this.cloudinary = cloudinary;
        this.cloudinaryEnvironment = cloudinaryEnvironment;
    }
    @Override
    public String createOneImage(String folder, File file) throws ImageProcessingException {
        Map<String, Object> uploadOptions = new HashMap<>();
        uploadOptions.put("folder", this.cloudinaryEnvironment + "/" + folder);
        Map<?, ?> result;
        try {
            result = this.cloudinary.uploader().upload(file, uploadOptions);
        } catch (IOException ioException) {
            throw new ImageProcessingException("No se pudo guardar la imagen en Cloudinary");
        }
        Object secureUrl = result.get("secure_url");
        if (!(secureUrl instanceof String)) {
            throw new ImageProcessingException("Cloudinary no devolvió una URL válida para la imagen subida");
        }
        if (file.exists() && file.isFile()) {
            long startTime = System.currentTimeMillis();
            boolean deleted = false;
            while (System.currentTimeMillis() - startTime < 3000) {
                try {
                    if (Files.deleteIfExists(file.toPath())) {
                        deleted = true;
                        break;
                    }
                } catch (IOException ioException) {
                    throw new ImageProcessingException("Error al intentar eliminar el archivo: " + file.getAbsolutePath());
                }
            }
            if (!deleted) {
                throw new ImageProcessingException("No se pudo eliminar el archivo temporal después de múltiples intentos: " + file.getAbsolutePath());
            }
        }
        return (String) secureUrl;
    }
    @Override
    public void deleteOneImage(String folder, String image) throws ImageProcessingException {
        try {
            String publicId = extractPublicId(this.cloudinaryEnvironment + "/" + folder, image);
            Map<?,?> result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            if (!"ok".equals(result.get("result"))) {
                throw new ImageProcessingException("No se pudo eliminar el archivo en Cloudinary");
            }
        } catch (IOException e) {
            throw new ImageProcessingException("Error al intentar eliminar el archivo en Cloudinary");
        }
    }
    private String extractPublicId(String imageUrl, String folderPath) {
        String filename = imageUrl.substring(imageUrl.lastIndexOf("/") + 1, imageUrl.lastIndexOf("."));
        return folderPath + "/" + filename;
    }
}
