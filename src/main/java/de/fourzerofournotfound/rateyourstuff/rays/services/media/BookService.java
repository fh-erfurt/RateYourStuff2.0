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
     * This service is used to check if a given book-object(checked by its attributes) is already stored in database
     * @param book - object which is streamed via rest api
     * @return true if a object is already stored in database (the entry of this book-object is valid)
     */
    public boolean isValidBook(Book book)
    {
        Optional<Book> optionalBook;
        if(Objects.nonNull(book.getId()))
        {
            optionalBook = bookRepository.findBookByIdNotAndMediumNameIgnoreCaseAndReleaseDateOrIsbn(book.getId(), book.getMediumName(), book.getReleaseDate(), book.getIsbn());
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
