package com.nxtep.infrastructure.jpa.mappers;

import com.nxtep.domain.models.User;
import com.nxtep.infrastructure.jpa.entities.UserEntity;

import java.time.LocalDateTime;

public class UserJpaMapper {
    private UserJpaMapper() {}
    public static UserEntity domainToEntity(User user) {
        return UserEntity.builder()
            .id(user.getId() == null ? null : user.getId())
            .username(user.getUsername())
            .phone(user.getPhone())
            .photo(user.getPhoto())
            .avatar(user.getAvatar() == null ? null : user.getAvatar())
            .createdAt(user.getCreatedAt() == null ? LocalDateTime.now() : user.getCreatedAt())
            .updatedAt(user.getUpdatedAt() == null ? LocalDateTime.now() : user.getUpdatedAt())
            .build();
    }
    public static User entityToDomain(UserEntity userEntity) {
        return User.builder()
            .id(userEntity.getId())
            .username(userEntity.getUsername())
            .phone(userEntity.getPhone())
            .photo(userEntity.getPhoto())
            .avatar(userEntity.getAvatar())
            .createdAt(userEntity.getCreatedAt())
            .updatedAt(userEntity.getUpdatedAt())
            .build();
    }
}
