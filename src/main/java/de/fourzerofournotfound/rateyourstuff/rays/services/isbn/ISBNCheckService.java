package de.fourzerofournotfound.rateyourstuff.rays.services.isbn;

import de.fourzerofournotfound.rateyourstuff.rays.models.media.Book;
import org.springframework.stereotype.Service;

/**
 * ISBN CheckService
 * <p>This service is used to check if an given isbn is valid and can be saved in the database</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Service("isbnCheckService")
public class ISBNCheckService {
    /**
     * Checks if the given book has either a valid ISBN10 or ISBN13
     *
     * @param book the book that should be checked
     * @return true, if the ISBN is valid, false, if the isbn is invalid
     */
    public boolean checkIfISBNisValid(Book book) {
        return (ISBN13.isValid(book.getIsbn()) || ISBN10.isValid(book.getIsbn()));
    }
}
