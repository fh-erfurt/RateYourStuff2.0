package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.BookDto;
import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.EpisodeDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Book;
import de.fourzerofournotfound.rateyourstuff.rays.models.Episode;
import de.fourzerofournotfound.rateyourstuff.rays.models.Rating;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bookService")
public class BookService {
    @Autowired
    ModelMapper modelMapper;

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
}
