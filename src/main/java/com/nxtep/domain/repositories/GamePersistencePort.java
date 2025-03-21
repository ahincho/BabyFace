package com.nxtep.domain.repositories;

import com.nxtep.domain.models.Game;

import java.time.LocalDateTime;

public interface GamePersistencePort {
    Game createOneGame(Game game);
    boolean existsOneGameByUserIdAndCreatedAtAfter(Integer userId, LocalDateTime createdAt);
}
