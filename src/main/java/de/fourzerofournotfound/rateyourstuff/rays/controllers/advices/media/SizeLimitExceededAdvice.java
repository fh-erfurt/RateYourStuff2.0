package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices.media;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <h1>SizeLimitExceededAdvice</h1>
 * <p>This Advice is used by the <ul>
 *     <li>{@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.MovieController MovieController}</li>
 *     <li>{@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.GameController GameController}</li>
 *     <li>{@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.SeriesController SeriesController}</li>
 *     <li>{@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.EpisodeController EpisodeController}</li>
 *     <li>{@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.BookController BookController}</li>
 * </ul></p>
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
