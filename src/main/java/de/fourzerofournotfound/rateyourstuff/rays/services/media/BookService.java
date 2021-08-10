package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.BookDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Book;
import de.fourzerofournotfound.rateyourstuff.rays.models.BookPublisher;
import de.fourzerofournotfound.rateyourstuff.rays.models.Rating;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.BookPublisherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("bookService")
public class BookService {
    private final ModelMapper modelMapper;
    private final BookPublisherRepository publisherRepository;

    @Autowired
    public BookService(ModelMapper modelMapper, BookPublisherRepository publisherRepository) {
        this.modelMapper = modelMapper;
        this.publisherRepository = publisherRepository;
    }

    /**
     * Converts a given book to a bookDTO object to limit the data that gets sent to the client
     * @param book   the book that should be converted
     * @return          the corresponding dtoObject
     */
    public BookDto convertToDto(Book book) {
        BookDto bookDto= modelMapper.map(book, BookDto.class);
        bookDto.setAverageRating(book.getMediumRatings());
        bookDto.setNumberOfRatings(book.getMediumRatings());
        bookDto.setMIN_RATING_POINTS(Rating.MIN_POINTS);
        bookDto.setMAX_RATING_POINTS(Rating.MAX_POINTS);
        bookDto.setNumberOfComments(book.getComments());
        bookDto.setNumberOfCollections(book.getCollections());
        return bookDto;
    }

    public BookPublisher getPublisher(String publisherString, Book book) {
        Optional<BookPublisher> publisher = publisherRepository.findByBookPublisherTitle(publisherString);
        if(publisher.isPresent()) {
            return publisher.get();
        } else {
            BookPublisher newPublisher = new BookPublisher();
            newPublisher.setBookPublisherTitle(publisherString);
            return publisherRepository.save(newPublisher);
        }
    }
}
