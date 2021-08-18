package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices.media;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.books.BookController;
import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.games.GameController;
import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.movies.MovieController;
import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.series.EpisodeController;
import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.series.SeriesController;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p>This Advice is used by the</p> <ul>
 * <li>{@link MovieController MovieController}</li>
 * <li>{@link GameController GameController}</li>
 * <li>{@link SeriesController SeriesController}</li>
 * <li>{@link EpisodeController EpisodeController}</li>
 * <li>{@link BookController BookController}</li>
 * </ul>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */

@ControllerAdvice
public class SizeLimitExceededAdvice {
    @ResponseBody
    @ExceptionHandler(SizeLimitExceededException.class)
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    String sizeLimitExceededHandler(SizeLimitExceededException ex) {
        return ex.getMessage();
    }
}
