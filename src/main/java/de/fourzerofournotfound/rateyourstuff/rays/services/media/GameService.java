package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.GameDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.*;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.GamePublisherRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.PlatformRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service("gameService")
public class GameService {
    private final ModelMapper modelMapper;
    private final PlatformRepository platformRepository;
    private final GamePublisherRepository gamePublisherRepository;

    @Autowired
    public GameService(ModelMapper modelMapper, PlatformRepository platformRepository, GamePublisherRepository gamePublisherRepository) {
        this.modelMapper = modelMapper;
        this.platformRepository = platformRepository;
        this.gamePublisherRepository = gamePublisherRepository;
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

    public Set<Platform> getPlatformSet(List<String> platformStrings, Game game) {
        Set<Platform> platforms = new HashSet<>();
        for (String platform : platformStrings) {
            Optional<Platform> foundPlatform = platformRepository.findByPlatformTitle(platform);
            if (foundPlatform.isPresent()) {
                foundPlatform.get().getGames().add(game);
                platforms.add(foundPlatform.get());
            } else {
                Platform newPlatform = new Platform();
                newPlatform.setPlatformTitle(platform);
                newPlatform.setGames(new HashSet<Medium>());
                newPlatform.getGames().add(game);
                platforms.add(platformRepository.save(newPlatform));
            }
        }
        return platforms;
    }

    public GamePublisher getPublisher(String publisherTitle, Game game) {
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
