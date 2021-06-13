package de.fourzerofournotfound.rateyourstuff.rays.services.isbn;

import de.fourzerofournotfound.rateyourstuff.rays.models.Book;
import org.springframework.stereotype.Service;

@Service("ics")
public class ISBNCheckService {
    public boolean checkIfISBNisValid(Book book) {
        return (ISBN13.isValid(book.getIsbn()) || ISBN10.isValid(book.getIsbn()));
    }
}
