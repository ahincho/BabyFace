package com.nxtep.presentations.rest.controllers;

import com.nxtep.domain.exceptions.UserDuplicateException;
import com.nxtep.domain.models.User;
import com.nxtep.presentations.rest.dtos.UserCreateRequest;
import com.nxtep.presentations.rest.dtos.UserResponse;
import com.nxtep.presentations.rest.mappers.UserRestMapper;
import com.nxtep.application.specifications.CreateOneUserUseCase;

import jakarta.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/users")
public class CreateOneUserRestController {
    private final CreateOneUserUseCase createOneUserUseCase;
    public CreateOneUserRestController(CreateOneUserUseCase createOneUserUseCase) {
        this.createOneUserUseCase = createOneUserUseCase;
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> createOneUser(
        @RequestBody @Valid UserCreateRequest userCreateRequest
    ) throws UserDuplicateException {
        User user = UserRestMapper.createRequestToDomain(userCreateRequest);
        User savedUser = this.createOneUserUseCase.execute(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).body(UserRestMapper.domainToResponse(savedUser));
    }
}
