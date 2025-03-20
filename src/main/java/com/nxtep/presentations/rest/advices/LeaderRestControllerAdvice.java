package com.nxtep.presentations.rest.advices;

import com.nxtep.domain.exceptions.LeaderNotFoundException;
import com.nxtep.presentations.rest.dtos.ExceptionResponse;
import com.nxtep.presentations.rest.utils.ExceptionResponseFactory;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LeaderRestControllerAdvice {
    private final ExceptionResponseFactory exceptionResponseFactory;
    public LeaderRestControllerAdvice(ExceptionResponseFactory exceptionResponseFactory) {
        this.exceptionResponseFactory = exceptionResponseFactory;
    }
    @ExceptionHandler(LeaderNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleLeaderNotFoundException(
        LeaderNotFoundException leaderNotFoundException,
        HttpServletRequest httpServletRequest
    ) {
        return this.exceptionResponseFactory.createErrorResponse(HttpStatus.NOT_FOUND, httpServletRequest, leaderNotFoundException.getMessage());
    }
}
