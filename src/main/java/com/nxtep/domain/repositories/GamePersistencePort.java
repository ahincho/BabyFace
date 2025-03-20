package com.nxtep.domain.repositories;

import com.nxtep.domain.models.Game;

public interface GamePersistencePort {
    Game createOneGame(Game game);
    boolean existsOneGameByUserId(Integer userId);
}
