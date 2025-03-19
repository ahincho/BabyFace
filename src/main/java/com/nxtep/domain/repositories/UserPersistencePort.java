package com.nxtep.domain.repositories;

import com.nxtep.domain.models.PageResult;
import com.nxtep.domain.models.User;

import java.util.Optional;

public interface UserPersistencePort {
    User createOneUser(User user);
    PageResult<User> findUsers(Integer page, Integer size);
    Optional<User> findOneUser(Integer userId);
    boolean existsOneUser(Integer userId);
    boolean existsOneUserByUsername(String username);
}
