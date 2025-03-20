package com.nxtep.application.services;

import com.nxtep.application.specifications.CreateOneUserPhotoUseCase;
import com.nxtep.domain.exceptions.UserNotFoundException;
import com.nxtep.domain.models.User;
import com.nxtep.domain.repositories.ImagePersistencePort;
import com.nxtep.domain.repositories.UserPersistencePort;

import org.springframework.stereotype.Service;

import java.io.File;
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
    public User execute(Integer userId, File photo) throws UserNotFoundException {
        Optional<User> optionalUser = this.userPersistencePort.findOneUser(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(String.format("User with id '%d' does not exist", userId));
        }
        String photoUrl = this.imagePersistencePort.createOneImage("photos", photo);
        User user = optionalUser.get();
        user.setPhoto(photoUrl);
        return this.userPersistencePort.updateOneUser(user);
    }
}
