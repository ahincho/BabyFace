package com.nxtep.application.services;

import com.nxtep.domain.exceptions.UserNotFoundException;
import com.nxtep.domain.models.Game;
import com.nxtep.domain.repositories.GamePersistencePort;
import com.nxtep.domain.repositories.UserPersistencePort;
import com.nxtep.application.specifications.CreateOneGameUseCase;

import org.springframework.stereotype.Service;

@Service
public class CreateOneGameService implements CreateOneGameUseCase {
    private final UserPersistencePort userPersistencePort;
    private final GamePersistencePort gamePersistencePort;
    public CreateOneGameService(UserPersistencePort userPersistencePort, GamePersistencePort gamePersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.gamePersistencePort = gamePersistencePort;
    }
    @Override
    public Game execute(Game game) throws UserNotFoundException {
        boolean existsUserById = this.userPersistencePort.existsOneUser(game.getUser().getId());
        if (!existsUserById) {
            throw new UserNotFoundException(String.format("User with id '%s' not found", game.getUser().getId()));
        }
        return this.gamePersistencePort.createOneGame(game);
    }
}
