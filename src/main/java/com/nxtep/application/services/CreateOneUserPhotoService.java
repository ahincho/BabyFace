package com.nxtep.application.services;

import com.nxtep.application.specifications.CreateOneUserPhotoUseCase;
import com.nxtep.domain.exceptions.ImageProcessingException;
import com.nxtep.domain.exceptions.UserNotFoundException;
import com.nxtep.domain.exceptions.UserValidationException;
import com.nxtep.domain.models.User;
import com.nxtep.domain.repositories.ImagePersistencePort;
import com.nxtep.domain.repositories.UserPersistencePort;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class CreateOneUserPhotoService implements CreateOneUserPhotoUseCase {
    private final UserPersistencePort userPersistencePort;
    private final ImagePersistencePort imagePersistencePort;
    public CreateOneUserPhotoService(UserPersistencePort userPersistencePort, ImagePersistencePort imagePersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.imagePersistencePort = imagePersistencePort;
    }
    @Override
    public User execute(Integer userId, File photo) throws UserNotFoundException, ImageProcessingException, UserValidationException {
        Optional<User> optionalUser = this.userPersistencePort.findOneUser(userId);
        if (optionalUser.isEmpty()) {
            deleteTempFile(photo);
            throw new UserNotFoundException(String.format("No existe un usuario con identificador '%s'", userId));
        }
        if (optionalUser.get().getPhoto() != null && !optionalUser.get().getPhoto().isEmpty()) {
            deleteTempFile(photo);
            throw new UserValidationException(String.format("El usuario con identificador '%s' ya posee una foto de perfil asociada", userId));
        }
        String photoUrl = this.imagePersistencePort.createOneImage("photos", photo);
        User user = optionalUser.get();
        user.setPhoto(photoUrl);
        return this.userPersistencePort.updateOneUser(user);
    }
    private void deleteTempFile(File file) throws ImageProcessingException {
        if (file != null && file.exists()) {
            try {
                Files.delete(file.toPath());
            } catch (IOException e) {
                throw new ImageProcessingException("Error al eliminar el archivo temporal: " + file.getAbsolutePath());
            }
        }
    }
}
