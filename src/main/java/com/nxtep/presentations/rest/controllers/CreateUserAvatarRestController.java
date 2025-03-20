package com.nxtep.presentations.rest.controllers;

import com.nxtep.domain.exceptions.UserNotFoundException;
import com.nxtep.domain.exceptions.UserValidationException;
import com.nxtep.domain.models.User;
import com.nxtep.presentations.rest.dtos.UserResponse;
import com.nxtep.presentations.rest.mappers.UserRestMapper;
import com.nxtep.application.specifications.CreateOneUserAvatarUseCase;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class CreateUserAvatarRestController {
    private final CreateOneUserAvatarUseCase createOneUserAvatarUseCase;
    public CreateUserAvatarRestController(CreateOneUserAvatarUseCase createOneUserAvatarUseCase) {
        this.createOneUserAvatarUseCase = createOneUserAvatarUseCase;
    }
    @PostMapping(value = "/{userId}/avatars", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> createUserAvatar(
        @PathVariable Integer userId
    ) throws UserNotFoundException, UserValidationException {
        User updatedUser = this.createOneUserAvatarUseCase.execute(userId);
        UserResponse userResponse = UserRestMapper.domainToResponse(updatedUser);
        return ResponseEntity.ok(userResponse);
    }
}
