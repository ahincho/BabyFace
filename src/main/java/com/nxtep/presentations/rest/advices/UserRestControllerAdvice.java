package com.nxtep.presentations.rest.advices;

import com.nxtep.domain.exceptions.UserDuplicateException;
import com.nxtep.domain.exceptions.UserNotFoundException;
import com.nxtep.presentations.rest.dtos.ExceptionResponse;
import com.nxtep.presentations.rest.utils.ExceptionResponseFactory;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserRestControllerAdvice {
    private final ExceptionResponseFactory exceptionResponseFactory;
    public UserRestControllerAdvice(ExceptionResponseFactory exceptionResponseFactory) {
        this.exceptionResponseFactory = exceptionResponseFactory;
    }
    @ExceptionHandler(UserDuplicateException.class)
    public ResponseEntity<ExceptionResponse> handleUserDuplicateException(
        UserDuplicateException userDuplicateException,
        HttpServletRequest httpServletRequest
    ) {
        return exceptionResponseFactory.createErrorResponse(HttpStatus.CONFLICT, httpServletRequest, userDuplicateException.getMessage());
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUserNotFoundException(
        UserNotFoundException userNotFoundException,
        HttpServletRequest httpServletRequest
    ) {
        return exceptionResponseFactory.createErrorResponse(HttpStatus.NOT_FOUND, httpServletRequest, userNotFoundException.getMessage());
    }
}
