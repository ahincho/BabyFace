package com.nxtep.presentations.rest.advices;

import com.nxtep.presentations.rest.dtos.ExceptionResponse;
import com.nxtep.presentations.rest.utils.ExceptionResponseFactory;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@RestControllerAdvice
public class GlobalRestControllerAdvice {
    private final ExceptionResponseFactory exceptionResponseFactory;
    public GlobalRestControllerAdvice(ExceptionResponseFactory exceptionResponseFactory) {
        this.exceptionResponseFactory = exceptionResponseFactory;
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentTypeMismatch(
        MethodArgumentTypeMismatchException methodArgumentTypeMismatchException,
        HttpServletRequest httpServletRequest
    ) {
        String message = String.format("Invalid value for parameter '%s'. Expected %s.",
            methodArgumentTypeMismatchException.getName(), getExpectedTypeMessage(methodArgumentTypeMismatchException.getRequiredType()));
        return this.exceptionResponseFactory.createErrorResponse(HttpStatus.BAD_REQUEST, httpServletRequest, message);
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionResponse> handleMethodNotSupported(
        HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException,
        HttpServletRequest httpServletRequest
    ) {
        return this.exceptionResponseFactory.createErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, httpServletRequest, httpRequestMethodNotSupportedException.getMessage());
    }
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMediaTypeNotAcceptable(
        HttpMediaTypeNotSupportedException httpMediaTypeNotSupportedException,
        HttpServletRequest httpServletRequest
    ) {
        return this.exceptionResponseFactory.createErrorResponse(HttpStatus.NOT_ACCEPTABLE, httpServletRequest, httpMediaTypeNotSupportedException.getMessage());
    }
    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<ExceptionResponse> handleMissingServletRequestPart(
        MissingServletRequestPartException missingServletRequestPartException,
        HttpServletRequest httpServletRequest
    ) {
        return this.exceptionResponseFactory.createErrorResponse(HttpStatus.BAD_REQUEST, httpServletRequest, missingServletRequestPartException.getMessage());
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadable(
        HttpServletRequest httpServletRequest
    ) {
        return this.exceptionResponseFactory.createErrorResponse(HttpStatus.BAD_REQUEST, httpServletRequest, "Required request body is missing");
    }
    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<ExceptionResponse> handleMultipartException(
        MultipartException multipartException,
        HttpServletRequest httpServletRequest
    ) {
        return this.exceptionResponseFactory.createErrorResponse(HttpStatus.NOT_ACCEPTABLE, httpServletRequest, multipartException.getMessage());
    }
    protected String getExpectedTypeMessage(Class<?> requiredType) {
        return switch (requiredType != null ? requiredType.getSimpleName() : "Unknown") {
            case "Integer" -> "an integer";
            case "Double" -> "a decimal number";
            case "Boolean" -> "a boolean (true/false)";
            case "String" -> "a string";
            default -> "a value of type " + (requiredType != null ? requiredType.getSimpleName() : "unknown");
        };
    }
}
