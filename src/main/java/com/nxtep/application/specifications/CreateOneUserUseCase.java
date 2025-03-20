package com.nxtep.application.specifications;

import com.nxtep.domain.exceptions.UserDuplicateException;
import com.nxtep.domain.models.User;

import java.io.File;

public interface CreateOneUserUseCase {
    User execute(User user, File photo) throws UserDuplicateException;
}
