package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.GameDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.*;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Game;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.GamePublisher;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Platform;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.GamePublisherRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.GameRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.PlatformRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <h1>Game Service</h1>
 * <p>This Service provides methods to the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.GameController GameController}</p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Service("gameService")
public class GameService {
    private final ModelMapper modelMapper;
    private final PlatformRepository platformRepository;
    private final GamePublisherRepository gamePublisherRepository;
    private final GameRepository gameRepository;

    @Autowired
    public GameService(ModelMapper modelMapper, PlatformRepository platformRepository, GamePublisherRepository gamePublisherRepository, GameRepository gameRepository) {
        this.modelMapper = modelMapper;
        this.platformRepository = platformRepository;
        this.gamePublisherRepository = gamePublisherRepository;
        this.gameRepository = gameRepository;
    }

    /**
     * Checks if a game can be stored within the database without causing duplicates
     * @param game the game that should be checked
     * @return true if the game is not already stored in the database
     */
    public boolean isValidGame(Game game)
    {
        Optional<Game> optionalGame;
        if(Objects.nonNull(game.getId()))
        {
            optionalGame = gameRepository.findGameByIdNotAndMediumNameIgnoreCaseAndReleaseDate(game.getId(), game.getMediumName(), game.getReleaseDate());
        } else {
            optionalGame = gameRepository.findGameByMediumNameIgnoreCaseAndReleaseDate(game.getMediumName(), game.getReleaseDate());
        }
        return optionalGame.isEmpty();
    }

    /**
     * Converts a given game to a gameDTO object to limit the data that gets sent to the client
     *
     * @param game the game that should be converted
     * @return the corresponding dtoObject
     */
    public GameDto convertToDto(Game game) {
        GameDto gameDto = modelMapper.map(game, GameDto.class);
        gameDto.setAverageRating(game.getMediumRatings());
        gameDto.setNumberOfRatings(game.getMediumRatings());
        gameDto.setMIN_RATING_POINTS(Rating.MIN_POINTS);
        gameDto.setMAX_RATING_POINTS(Rating.MAX_POINTS);
        gameDto.setNumberOfComments(game.getComments());
        gameDto.setNumberOfCollections(game.getCollections());
        return gameDto;
    }

    /**
     * Returns references to the given platform names. Creates the platforms, if they do not exist
     * @param platformStrings   the names of the platforms that should be references
     * @return                  the entities of the platforms
     */
    public Set<Platform> getPlatformSet(List<String> platformStrings) {
        Set<Platform> platforms = new HashSet<>();
        for (String platform : platformStrings) {
            Optional<Platform> foundPlatform = platformRepository.findByPlatformTitle(platform);
            if (foundPlatform.isPresent()) {
                  platforms.add(foundPlatform.get());
            } else {
                Platform newPlatform = new Platform();
                newPlatform.setPlatformTitle(platform);
                platforms.add(platformRepository.save(newPlatform));
            }
        }
        return platforms;
    }

    /**
     * Returns references to the given publisher names. Creates publishers, if they do not exist.
     * @param publisherTitle    the names of the publishers that should be references
     * @return                  the entities of the publishers
     */
    public GamePublisher getPublisher(String publisherTitle) {
        Optional<GamePublisher> publisher = gamePublisherRepository.findByGamePublisherTitle(publisherTitle);
        if(publisher.isPresent()) {
            return publisher.get();
        } else {
            GamePublisher newPublisher = new GamePublisher();
            newPublisher.setGamePublisherTitle(publisherTitle);
            return gamePublisherRepository.save(newPublisher);
        }
    }
}
