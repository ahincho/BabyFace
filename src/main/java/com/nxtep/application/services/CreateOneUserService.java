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
        boolean existsByPhone = this.userPersistencePort.existsOneUserByPhone(user.getPhone());
        if (existsByPhone) {
            throw new UserDuplicateException(String.format("Usuario con número telefónico '%s' registrado anteriormente", user.getPhone()));
        }
        return this.userPersistencePort.createOneUser(user);
    }
}
