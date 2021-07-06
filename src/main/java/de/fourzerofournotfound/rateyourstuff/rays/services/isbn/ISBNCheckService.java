package de.fourzerofournotfound.rateyourstuff.rays.services.isbn;

import de.fourzerofournotfound.rateyourstuff.rays.models.Book;
import org.springframework.stereotype.Service;

@Service("ics")
public class ISBNCheckService {
    /**
     * Checks if the given book has either a valid ISBN10 or ISBN13
     * @param book the book that should be checked
     * @return true, if the ISBN is valid, false, if the isbn is invalid
     */
    public boolean checkIfISBNisValid(Book book) {
        return (ISBN13.isValid(book.getIsbn()) || ISBN10.isValid(book.getIsbn()));
    }
}
