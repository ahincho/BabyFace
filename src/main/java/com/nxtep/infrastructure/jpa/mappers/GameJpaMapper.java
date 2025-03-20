package com.nxtep.infrastructure.jpa.mappers;

import com.nxtep.domain.models.Game;
import com.nxtep.domain.models.User;
import com.nxtep.infrastructure.jpa.entities.GameEntity;
import com.nxtep.infrastructure.jpa.entities.UserEntity;

import java.time.LocalDateTime;

public class GameJpaMapper {
    private GameJpaMapper() {}
    public static GameEntity domainToEntity(Game game) {
        return GameEntity.builder()
            .id(game.getId() == null ? null : game.getId())
            .user(
                UserEntity.builder()
                    .id(game.getUser().getId())
                    .build()
            )
            .points(game.getPoints())
            .createdAt(game.getCreatedAt() == null ? LocalDateTime.now() : game.getCreatedAt())
            .updatedAt(game.getUpdatedAt() == null ? LocalDateTime.now() : game.getUpdatedAt())
            .build();
    }
    public static Game entityToEntity(GameEntity gameEntity) {
        return Game.builder()
            .id(gameEntity.getId())
            .user(
                User.builder()
                    .id(gameEntity.getUser().getId())
                    .build()
            )
            .points(gameEntity.getPoints())
            .createdAt(gameEntity.getCreatedAt())
            .updatedAt(gameEntity.getUpdatedAt())
            .build();
    }
}
