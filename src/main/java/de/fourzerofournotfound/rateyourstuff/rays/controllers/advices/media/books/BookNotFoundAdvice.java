package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices.media.books;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.books.BookController;
import de.fourzerofournotfound.rateyourstuff.rays.errors.media.books.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p>This Advice is used by the {@link BookController BookController}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@ControllerAdvice
public class BookNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String bookNotFoundHandler(BookNotFoundException ex) {
        return ex.getMessage();
    }
}
