package com.nxtep.presentations.rest.advices;

import com.nxtep.domain.exceptions.GameNotFoundException;
import com.nxtep.presentations.rest.dtos.ExceptionResponse;
import com.nxtep.presentations.rest.utils.ExceptionResponseFactory;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GameRestControllerAdvice {
    private final ExceptionResponseFactory exceptionResponseFactory;
    public GameRestControllerAdvice(ExceptionResponseFactory exceptionResponseFactory) {
        this.exceptionResponseFactory = exceptionResponseFactory;
    }
    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleGameNotFoundException(
        GameNotFoundException gameNotFoundException,
        HttpServletRequest httpServletRequest
    ) {
        return this.exceptionResponseFactory.createErrorResponse(HttpStatus.NOT_FOUND, httpServletRequest, gameNotFoundException.getMessage());
    }
}
