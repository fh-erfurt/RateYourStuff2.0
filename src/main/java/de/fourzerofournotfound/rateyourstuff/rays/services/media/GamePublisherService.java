package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.GamePublisherDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.GamePublisher;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GamePublisherService {
    private final ModelMapper modelMapper;

    @Autowired
    public GamePublisherService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public GamePublisherDto convertToDto(GamePublisher publisher) {
        return (modelMapper.map(publisher, GamePublisherDto.class));
    }
}
