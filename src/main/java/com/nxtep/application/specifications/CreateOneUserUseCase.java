package com.nxtep.application.specifications;

import com.nxtep.domain.exceptions.UserDuplicateException;
import com.nxtep.domain.models.User;

public interface CreateOneUserUseCase {
    User execute(User user) throws UserDuplicateException;
}
