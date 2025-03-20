package com.nxtep.presentations.rest.advices;

import com.nxtep.domain.exceptions.ImageConversionException;
import com.nxtep.domain.exceptions.ImageProcessingException;
import com.nxtep.presentations.rest.dtos.ExceptionResponse;
import com.nxtep.presentations.rest.utils.ExceptionResponseFactory;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ImageRestControllerAdvice {
    private final ExceptionResponseFactory exceptionResponseFactory;
    public ImageRestControllerAdvice(ExceptionResponseFactory exceptionResponseFactory) {
        this.exceptionResponseFactory = exceptionResponseFactory;
    }
    @ExceptionHandler(ImageConversionException.class)
    public ResponseEntity<ExceptionResponse> handleImageConversionException(
        ImageConversionException imageConversionException,
        HttpServletRequest httpServletRequest
    ) {
        return this.exceptionResponseFactory.createErrorResponse(HttpStatus.BAD_REQUEST, httpServletRequest, imageConversionException.getMessage());
    }
    @ExceptionHandler(ImageProcessingException.class)
    public ResponseEntity<ExceptionResponse> handleImageProcessingException(
        ImageProcessingException imageProcessingException,
        HttpServletRequest httpServletRequest
    ) {
        return this.exceptionResponseFactory.createErrorResponse(HttpStatus.FAILED_DEPENDENCY, httpServletRequest, imageProcessingException.getMessage());
    }
}
