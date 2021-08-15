package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.GamePublisherDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.GamePublisher;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h1>GamePublisherService</h1>
 * <p>This Service is used to provide methods to the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.GamePublisherController GamePublisherController}</p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Service
public class GamePublisherService {
    private final ModelMapper modelMapper;

    @Autowired
    public GamePublisherService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Converts a given GamePublisher to a GamePublisherDTO
     * @param publisher the publisher that should be converted
     * @return          the converted publisher
     */
    public GamePublisherDto convertToDto(GamePublisher publisher) {
        return (modelMapper.map(publisher, GamePublisherDto.class));
    }
}
