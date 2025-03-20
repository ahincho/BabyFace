package com.nxtep.services.implementations;

import com.nxtep.domain.exceptions.UserNotFoundException;
import com.nxtep.domain.exceptions.UserValidationException;
import com.nxtep.domain.models.User;
import com.nxtep.domain.services.ImageGeneratorPort;
import com.nxtep.domain.repositories.ImagePersistencePort;
import com.nxtep.domain.repositories.UserPersistencePort;
import com.nxtep.services.specifications.CreateOneUserAvatarUseCase;

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
    public User execute(Integer userId) throws UserNotFoundException, UserValidationException {
        Optional<User> optionalUser = this.userPersistencePort.findOneUser(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(String.format("User with id '%d' not found", userId));
        }
        User user = optionalUser.get();
        if (user.getPhoto().isEmpty()) {
            throw new UserValidationException(String.format("The user with id '%d' does not have an associated photo", userId));
        }
        if (user.getAvatar() != null) {
            throw new UserValidationException(String.format("The user with id '%d' has an associated avatar", userId));
        }
        File avatar = this.imageGeneratorPort.createImageFromAnotherImage(user.getPhoto());
        String avatarUrl = this.imagePersistencePort.createOneImage("avatars", avatar);
        user.setAvatar(avatarUrl);
        return this.userPersistencePort.updateOneUser(user);
    }
}
