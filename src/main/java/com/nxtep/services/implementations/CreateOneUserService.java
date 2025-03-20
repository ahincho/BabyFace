package com.nxtep.services.implementations;

import com.nxtep.domain.exceptions.UserDuplicateException;
import com.nxtep.domain.models.User;
import com.nxtep.domain.repositories.ImagePersistencePort;
import com.nxtep.domain.repositories.UserPersistencePort;
import com.nxtep.services.specifications.CreateOneUserUseCase;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class CreateOneUserService implements CreateOneUserUseCase {
    private final UserPersistencePort userPersistencePort;
    private final ImagePersistencePort imagePersistencePort;
    public CreateOneUserService(UserPersistencePort userPersistencePort, ImagePersistencePort imagePersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.imagePersistencePort = imagePersistencePort;
    }
    @Override
    public User execute(User user, File photo) throws UserDuplicateException {
        boolean existsByUsername = this.userPersistencePort.existsOneUserByUsername(user.getUsername());
        if (existsByUsername) {
            throw new UserDuplicateException(String.format("Usuario con nombre '%s' registrado anteriormente", user.getUsername()));
        }
        String photoUrl = this.imagePersistencePort.createOneImage("photos", photo);
        user.setPhoto(photoUrl);
        return this.userPersistencePort.createOneUser(user);
    }
}
