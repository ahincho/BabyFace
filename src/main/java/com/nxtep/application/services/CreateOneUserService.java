package com.nxtep.application.services;

import com.nxtep.domain.exceptions.UserDuplicateException;
import com.nxtep.domain.models.User;
import com.nxtep.domain.repositories.UserPersistencePort;
import com.nxtep.application.specifications.CreateOneUserUseCase;

import org.springframework.stereotype.Service;

@Service
public class CreateOneUserService implements CreateOneUserUseCase  {
    private final UserPersistencePort userPersistencePort;
    public CreateOneUserService(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }
    @Override
    public User execute(User user) throws UserDuplicateException {
        boolean existsByUsername = this.userPersistencePort.existsOneUserByUsername(user.getUsername());
        if (existsByUsername) {
            throw new UserDuplicateException(String.format("Usuario con nombre '%s' registrado anteriormente", user.getUsername()));
        }
        return this.userPersistencePort.createOneUser(user);
    }
}
