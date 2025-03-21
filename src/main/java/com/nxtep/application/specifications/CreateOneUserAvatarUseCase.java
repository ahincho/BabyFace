package com.nxtep.application.specifications;

import com.nxtep.domain.exceptions.ImageProcessingException;
import com.nxtep.domain.exceptions.UserNotFoundException;
import com.nxtep.domain.exceptions.UserValidationException;
import com.nxtep.domain.models.User;

public interface CreateOneUserAvatarUseCase {
    User execute(Integer userId) throws UserNotFoundException, UserValidationException, ImageProcessingException;
}
