package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.BookDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Book;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.BookPublisher;
import de.fourzerofournotfound.rateyourstuff.rays.models.Rating;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.BookPublisherRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * <h1>BookService</h1>
 * <p>This Service is used to provide different book-handling methods to the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.BookController BookController}</p>
 */
@Service("bookService")
public class BookService {
    private final ModelMapper modelMapper;
    private final BookPublisherRepository publisherRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BookService(ModelMapper modelMapper, BookPublisherRepository publisherRepository, BookRepository bookRepository) {
        this.modelMapper = modelMapper;
        this.publisherRepository = publisherRepository;
        this.bookRepository = bookRepository;
    }

    /**
     * Checks if a book can be stored within the database without causing duplicates
     * @param book the book that should be checked
     * @return true if the book is not already stored in the database
     */
    public boolean isValidBook(Book book)
    {
        Optional<Book> optionalBook;
        if(Objects.nonNull(book.getId()))
        {
            optionalBook = bookRepository.findBookByIdNotAndIsbn(book.getId(), book.getIsbn());
        } else {
            optionalBook = bookRepository.findBookByMediumNameIgnoreCaseAndReleaseDateOrIsbn(book.getMediumName(), book.getReleaseDate(), book.getIsbn());
        }
        return optionalBook.isEmpty();
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

    /**
     * Returns the entity of a book publisher from the database. Creates a new Book publisher, if there is no
     * publisher with the given string as title
     * @param publisherString   the publisher title that should be searched within the database
     * @return  the valid book publisher entity from the database
     */
    public BookPublisher getPublisher(String publisherString) {
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
