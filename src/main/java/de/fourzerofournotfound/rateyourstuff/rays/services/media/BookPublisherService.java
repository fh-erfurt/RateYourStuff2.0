package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.BookPublisherDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.BookPublisher;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h1>BookPublisherService</h1>
 * <p>This service is provides methods for the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.BookPublisherController BookPublisherController}</p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Service("bookPublisherService")
public class BookPublisherService {
    private final ModelMapper modelMapper;

    @Autowired
    public BookPublisherService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Converts a given Book Publisher to a BookPublisher DTO
     * @param publisher the publisher that should be converted
     * @return  the converted publisher
     */
    public BookPublisherDto convertToDto(BookPublisher publisher) {
        return modelMapper.map(publisher, BookPublisherDto.class);
    }
}
