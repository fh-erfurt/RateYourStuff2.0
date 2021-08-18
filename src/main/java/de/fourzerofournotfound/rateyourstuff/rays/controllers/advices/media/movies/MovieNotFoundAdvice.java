package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices.media.movies;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.movies.MovieController;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.movies.MovieNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * MovieNotFoundAdviceAdvice
 * <p>This Advice is used by the {@link MovieController MovieController}
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@ControllerAdvice
public class MovieNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(MovieNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String movieNotFoundHandler(MovieNotFoundException ex) {
        return ex.getMessage();
    }
}
