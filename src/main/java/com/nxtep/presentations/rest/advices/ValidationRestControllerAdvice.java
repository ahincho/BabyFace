package com.nxtep.presentations.rest.advices;

import com.nxtep.presentations.rest.dtos.NotValidField;
import com.nxtep.presentations.rest.dtos.NotValidFieldsResponse;
import com.nxtep.presentations.rest.utils.ExceptionResponseFactory;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ValidationRestControllerAdvice {
    private final ExceptionResponseFactory exceptionResponseFactory;
    public ValidationRestControllerAdvice(ExceptionResponseFactory exceptionResponseFactory) {
        this.exceptionResponseFactory = exceptionResponseFactory;
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<NotValidFieldsResponse> handleConstraintViolation(
        ConstraintViolationException constraintViolationException,
        HttpServletRequest httpServletRequest
    ) {
        List<NotValidField> notValidFields = constraintViolationException.getConstraintViolations().stream()
            .map(NotValidField::new)
            .toList();
        return exceptionResponseFactory.createValidationErrorResponse(HttpStatus.BAD_REQUEST, httpServletRequest, notValidFields);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<NotValidFieldsResponse> handleMethodArgumentNotValid(
        MethodArgumentNotValidException methodArgumentNotValidException,
        HttpServletRequest httpServletRequest
    ) {
        List<NotValidField> notValidFields = methodArgumentNotValidException.getFieldErrors().stream()
            .map(NotValidField::new)
            .toList();
        return exceptionResponseFactory.createValidationErrorResponse(HttpStatus.BAD_REQUEST, httpServletRequest, notValidFields);
    }
}
