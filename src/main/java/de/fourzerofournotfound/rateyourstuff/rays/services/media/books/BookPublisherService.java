package de.fourzerofournotfound.rateyourstuff.rays.services.media.books;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.books.BookPublisherController;
import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.books.BookPublisherDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.books.BookPublisher;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.books.BookPublisherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

/**
 * <p>This service is provides methods for the {@link BookPublisherController BookPublisherController}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Service("bookPublisherService")
public class BookPublisherService {
    private final ModelMapper modelMapper;
    private final BookPublisherRepository publisherRepository;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    public BookPublisherService(ModelMapper modelMapper, BookPublisherRepository publisherRepository) {
        this.modelMapper = modelMapper;
        this.publisherRepository = publisherRepository;
    }

    /**
     * Converts a given Book Publisher to a BookPublisher DTO
     *
     * @param publisher the publisher that should be converted
     * @return the converted publisher
     */
    public BookPublisherDto convertToDto(BookPublisher publisher) {
        return modelMapper.map(publisher, BookPublisherDto.class);
    }

    /**
     * Returns the entity of a book publisher from the database. Creates a new Book publisher, if there is no
     * publisher with the given string as title
     *
     * @param publisherString the publisher title that should be searched within the database
     * @return the valid book publisher entity from the database
     */
    public BookPublisher getPublisher(String publisherString) {
        Optional<BookPublisher> publisher = publisherRepository.findByBookPublisherTitle(publisherString);
        if (publisher.isPresent()) {
            return publisher.get();
        } else {
            BookPublisher newPublisher = new BookPublisher();
            newPublisher.setBookPublisherTitle(publisherString);
            BookPublisher savedPublisher = publisherRepository.save(newPublisher);
            logger.info("Added " + BookPublisher.class.getSimpleName() + " with id " + savedPublisher.getId());
            return savedPublisher;
        }
    }
}
