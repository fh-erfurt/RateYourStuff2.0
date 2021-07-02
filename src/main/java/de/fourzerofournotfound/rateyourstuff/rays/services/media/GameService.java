package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.GameDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Game;
import de.fourzerofournotfound.rateyourstuff.rays.models.Rating;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("gameService")
public class GameService {
    @Autowired
    ModelMapper modelMapper;

    /**
     * Converts a given game to a gameDTO object to limit the data that gets sent to the client
     * @param game   the game that should be converted
     * @return          the corresponding dtoObject
     */
    public GameDto convertToDto(Game game) {
        GameDto gameDto= modelMapper.map(game, GameDto.class);
        gameDto.setAverageRating(game.getMediumRatings());
        gameDto.setNumberOfRatings(game.getMediumRatings());
        gameDto.setMIN_RATING_POINTS(Rating.MIN_POINTS);
        gameDto.setMAX_RATING_POINTS(Rating.MAX_POINTS);
        gameDto.setNumberOfComments(game.getComments());
        gameDto.setNumberOfCollections(game.getCollections());
        return gameDto;
    }
}
