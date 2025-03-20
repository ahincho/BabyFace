package com.nxtep.presentations.rest.controllers;

import com.nxtep.application.specifications.CreateOneUserPhotoUseCase;
import com.nxtep.domain.exceptions.ImageConversionException;
import com.nxtep.domain.exceptions.ImageProcessingException;
import com.nxtep.domain.exceptions.UserNotFoundException;
import com.nxtep.domain.exceptions.UserValidationException;
import com.nxtep.domain.models.User;
import com.nxtep.presentations.rest.dtos.UserResponse;
import com.nxtep.presentations.rest.mappers.ImageRestMapper;
import com.nxtep.presentations.rest.mappers.UserRestMapper;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/users")
public class CreateUserPhotoRestController {
    private final CreateOneUserPhotoUseCase createOneUserPhotoUseCase;
    public CreateUserPhotoRestController(CreateOneUserPhotoUseCase createOneUserPhotoUseCase) {
        this.createOneUserPhotoUseCase = createOneUserPhotoUseCase;
    }
    @PostMapping(
        value = "/{userId}/photos",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserResponse> createOneUserPhoto(
        @PathVariable Integer userId,
        @RequestPart MultipartFile photo
    ) throws ImageConversionException, UserNotFoundException, UserValidationException, ImageProcessingException {
        if (photo == null || photo.isEmpty()) {
            throw new ImageConversionException("La fotografía no debe estar vacía");
        }
        File photoFile = ImageRestMapper.multipartToFile(photo);
        User user = this.createOneUserPhotoUseCase.execute(userId, photoFile);
        UserResponse userResponse = UserRestMapper.domainToResponse(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}").buildAndExpand(userResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(userResponse);
    }
}
