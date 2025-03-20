package com.nxtep.application.services;

import com.nxtep.domain.exceptions.UserNotFoundException;
import com.nxtep.domain.models.User;
import com.nxtep.domain.repositories.UserPersistencePort;
import com.nxtep.application.specifications.FindOneUserUseCase;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindOneUserService implements FindOneUserUseCase {
    public final UserPersistencePort userPersistencePort;
    public FindOneUserService(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }
    @Override
    public User execute(Integer userId) throws UserNotFoundException {
        Optional<User> user = this.userPersistencePort.findOneUser(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException(String.format("No existe un usuario con identificador '%s'", userId));
        }
        return user.get();
    }
}
