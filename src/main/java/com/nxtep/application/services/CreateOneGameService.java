package com.nxtep.application.services;

import com.nxtep.domain.exceptions.GameDuplicateException;
import com.nxtep.domain.exceptions.UserNotFoundException;
import com.nxtep.domain.models.Game;
import com.nxtep.domain.repositories.GamePersistencePort;
import com.nxtep.domain.repositories.UserPersistencePort;
import com.nxtep.application.specifications.CreateOneGameUseCase;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class CreateOneGameService implements CreateOneGameUseCase {
    private final UserPersistencePort userPersistencePort;
    private final GamePersistencePort gamePersistencePort;
    public CreateOneGameService(UserPersistencePort userPersistencePort, GamePersistencePort gamePersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.gamePersistencePort = gamePersistencePort;
    }
    @Override
    public Game execute(Game game) throws UserNotFoundException, GameDuplicateException {
        boolean existsUserById = this.userPersistencePort.existsOneUser(game.getUser().getId());
        if (!existsUserById) {
            throw new UserNotFoundException(String.format("No existe un usuario con identificador '%s'", game.getUser().getId()));
        }
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        boolean alreadyPlayed = this.gamePersistencePort.existsOneGameByUserIdAndCreatedAtAfter(game.getUser().getId(), startOfDay);
        if (alreadyPlayed) {
            throw new GameDuplicateException(String.format("Ya existe una partida registrada anteriormente para el usuario con identificador '%s' el día de hoy", game.getUser().getId()));
        }
        return this.gamePersistencePort.createOneGame(game);
    }
}
