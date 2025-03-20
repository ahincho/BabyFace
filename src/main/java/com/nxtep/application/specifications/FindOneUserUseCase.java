package com.nxtep.application.specifications;

import com.nxtep.domain.exceptions.UserNotFoundException;
import com.nxtep.domain.models.User;

public interface FindOneUserUseCase {
    User execute(Integer userId) throws UserNotFoundException;
}
