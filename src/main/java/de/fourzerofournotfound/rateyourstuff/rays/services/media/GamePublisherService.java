package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.GamePublisherDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.GamePublisher;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.GamePublisherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

/**
 * GamePublisherService
 * <p>This Service is used to provide methods to the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.GamePublisherController GamePublisherController}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Service
public class GamePublisherService {
    private final ModelMapper modelMapper;
    private final GamePublisherRepository gamePublisherRepository;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    public GamePublisherService(ModelMapper modelMapper,
                                GamePublisherRepository gamePublisherRepository) {
        this.modelMapper = modelMapper;
        this.gamePublisherRepository = gamePublisherRepository;
    }

    /**
     * Converts a given GamePublisher to a GamePublisherDTO
     *
     * @param publisher the publisher that should be converted
     * @return the converted publisher
     */
    public GamePublisherDto convertToDto(GamePublisher publisher) {
        return (modelMapper.map(publisher, GamePublisherDto.class));
    }

    /**
     * Returns references to the given publisher names. Creates publishers, if they do not exist.
     *
     * @param publisherTitle the names of the publishers that should be references
     * @return the entities of the publishers
     */
    public GamePublisher getPublisher(String publisherTitle) {
        Optional<GamePublisher> publisher = gamePublisherRepository.findByGamePublisherTitle(publisherTitle);
        if (publisher.isPresent()) {
            return publisher.get();
        } else {
            GamePublisher newPublisher = new GamePublisher();
            newPublisher.setGamePublisherTitle(publisherTitle);
            GamePublisher savedPublisher = gamePublisherRepository.save(newPublisher);
            logger.info("Added " + GamePublisher.class.getSimpleName() + "with id " + savedPublisher.getId());
            return savedPublisher;
        }
    }
}
