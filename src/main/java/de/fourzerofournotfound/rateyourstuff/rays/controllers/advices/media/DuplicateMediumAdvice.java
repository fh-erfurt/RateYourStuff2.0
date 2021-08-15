package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices.media;

import de.fourzerofournotfound.rateyourstuff.rays.services.errors.DuplicateMediumException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <h1>DuplicateMediumAdvice</h1>
 * <p>This Advice is used by the <ul>
 *     <li>{@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.BookController BookController}</li>
 *     <li>{@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.EpisodeController EpisodeController}</li>
 *     <li>{@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.GameController GameCOntroller}</li>
 *     <li>{@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.MovieController MovieController}</li>
 *     <li>{@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.SeasonController SeasonController}</li>
 *     <li>{@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.SeriesController SeriesController}</li>
 * </ul></p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@ControllerAdvice
public class DuplicateMediumAdvice {
    @ResponseBody
    @ExceptionHandler(DuplicateMediumException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String duplicateMediumHandler(DuplicateMediumException ex) {
        return ex.getMessage();
    }
}
