package com.nxtep.presentations.rest.mappers;

import com.nxtep.domain.models.User;
import com.nxtep.presentations.rest.dtos.UserCreateRequest;
import com.nxtep.presentations.rest.dtos.UserResponse;

public class UserRestMapper {
    private UserRestMapper() {}
    public static User createRequestToDomain(UserCreateRequest userCreateRequest) {
        return User.builder()
            .username(userCreateRequest.getUsername())
            .phone(userCreateRequest.getPhone())
            .build();
    }
    public static UserResponse domainToResponse(User user) {
        return UserResponse.builder()
            .id(user.getId())
            .username(user.getUsername())
            .phone(user.getPhone())
            .photo(user.getPhoto())
            .createdAt(user.getCreatedAt())
            .updatedAt(user.getUpdatedAt())
            .build();
    }
}
