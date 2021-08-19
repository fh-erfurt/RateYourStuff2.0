package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.RatingController;
import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.books.BookController;
import de.fourzerofournotfound.rateyourstuff.rays.errors.InvalidRatingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p>This Advice is used by the {@link RatingController RatingController}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@ControllerAdvice
public class RatingInvalidAdvice {
    @ResponseBody
    @ExceptionHandler(InvalidRatingException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    String ratingInvalidHandler(InvalidRatingException ex) {
        return ex.getMessage();
    }
}
