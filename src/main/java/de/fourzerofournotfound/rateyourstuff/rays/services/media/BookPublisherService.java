package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.BookPublisherDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.BookPublisher;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service("bookPublisherService")
public class BookPublisherService {
    private final ModelMapper modelMapper;

    public BookPublisherService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BookPublisherDto convertToDto(BookPublisher publisher) {
        return modelMapper.map(publisher, BookPublisherDto.class);
    }
}
