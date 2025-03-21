package com.nxtep.application.services;

import com.nxtep.domain.exceptions.ImageProcessingException;
import com.nxtep.domain.exceptions.UserNotFoundException;
import com.nxtep.domain.exceptions.UserValidationException;
import com.nxtep.domain.models.User;
import com.nxtep.domain.services.ImageGeneratorPort;
import com.nxtep.domain.repositories.ImagePersistencePort;
import com.nxtep.domain.repositories.UserPersistencePort;
import com.nxtep.application.specifications.CreateOneUserAvatarUseCase;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Optional;

@Service
public class CreateOneUserAvatarService implements CreateOneUserAvatarUseCase {
    private final UserPersistencePort userPersistencePort;
    private final ImageGeneratorPort imageGeneratorPort;
    private final ImagePersistencePort imagePersistencePort;
    public CreateOneUserAvatarService(
        UserPersistencePort userPersistencePort,
        ImageGeneratorPort imageGeneratorPort,
        ImagePersistencePort imagePersistencePort
    ) {
        this.userPersistencePort = userPersistencePort;
        this.imageGeneratorPort = imageGeneratorPort;
        this.imagePersistencePort = imagePersistencePort;
    }
    @Override
    public User execute(Integer userId) throws UserNotFoundException, UserValidationException, ImageProcessingException {
        Optional<User> optionalUser = this.userPersistencePort.findOneUser(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(String.format("No existe un usuario con identificador '%s'", userId));
        }
        User user = optionalUser.get();
        if (user.getPhoto() == null || user.getPhoto().isEmpty()) {
            throw new UserValidationException(String.format("El usuario con identificador '%d' no tiene una foto de perfil asociada previamente", userId));
        }
        if (user.getAvatar() != null && !user.getAvatar().isEmpty()) {
            throw new UserValidationException(String.format("El usuario con identificador '%d' ya posee un avatar asociado previamente", userId));
        }
        File avatar = this.imageGeneratorPort.createImageFromAnotherImage(user.getUsername(), user.getPhoto());
        String avatarUrl = this.imagePersistencePort.createOneImage("avatars", avatar);
        user.setAvatar(avatarUrl);
        return this.userPersistencePort.updateOneUser(user);
    }
}
