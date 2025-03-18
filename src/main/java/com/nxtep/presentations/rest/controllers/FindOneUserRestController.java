package com.nxtep.presentations.rest.controllers;

import com.nxtep.domain.exceptions.UserNotFoundException;
import com.nxtep.domain.models.User;
import com.nxtep.presentations.rest.dtos.UserResponse;
import com.nxtep.presentations.rest.mappers.UserRestMapper;
import com.nxtep.services.specifications.FindOneUserUseCase;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class FindOneUserRestController {
    private final FindOneUserUseCase findOneUserUseCase;
    public FindOneUserRestController(FindOneUserUseCase findOneUserUseCase) {
        this.findOneUserUseCase = findOneUserUseCase;
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> findOneUser(
        @PathVariable Integer userId
    ) throws UserNotFoundException {
        User user = this.findOneUserUseCase.execute(userId);
        return ResponseEntity.ok(UserRestMapper.domainToResponse(user));
    }
}
