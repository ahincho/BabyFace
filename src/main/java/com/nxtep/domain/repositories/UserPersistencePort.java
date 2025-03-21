package com.nxtep.domain.repositories;

import com.nxtep.domain.models.User;

import java.util.Optional;

public interface UserPersistencePort {
    User createOneUser(User user);
    Optional<User> findOneUser(Integer userId);
    boolean existsOneUser(Integer userId);
    boolean existsOneUserByPhone(String phone);
    User updateOneUser(User user);
}
