package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.BookPublisherNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <h1>BookPublisherNotFoundAdvice</h1>
 * <p>This Advice is used by the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.BookPublisherController BookPublisherController}</p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@ControllerAdvice
public class BookPublisherNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(BookPublisherNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String bookPublisherNotFoundHandler(BookPublisherNotFoundException ex) {
        return ex.getMessage();
    }
}
