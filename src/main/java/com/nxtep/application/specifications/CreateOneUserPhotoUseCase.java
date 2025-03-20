package com.nxtep.application.specifications;

import com.nxtep.domain.exceptions.ImageProcessingException;
import com.nxtep.domain.exceptions.UserNotFoundException;
import com.nxtep.domain.exceptions.UserValidationException;
import com.nxtep.domain.models.User;

import java.io.File;

public interface CreateOneUserPhotoUseCase {
    User execute(Integer userId, File photo) throws UserNotFoundException, ImageProcessingException, UserValidationException;
}
