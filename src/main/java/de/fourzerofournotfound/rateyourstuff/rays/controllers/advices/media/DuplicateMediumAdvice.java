package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices.media;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.books.BookController;
import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.games.GameController;
import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.movies.MovieController;
import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.series.EpisodeController;
import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.series.SeasonController;
import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.series.SeriesController;
import de.fourzerofournotfound.rateyourstuff.rays.errors.media.DuplicateMediumException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p>This Advice is used by the </p><ul>
 * <li>{@link BookController BookController}</li>
 * <li>{@link EpisodeController EpisodeController}</li>
 * <li>{@link GameController GameCOntroller}</li>
 * <li>{@link MovieController MovieController}</li>
 * <li>{@link SeasonController SeasonController}</li>
 * <li>{@link SeriesController SeriesController}</li>
 * </ul>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@ControllerAdvice
public class DuplicateMediumAdvice {
    @ResponseBody
    @ExceptionHandler(DuplicateMediumException.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    String duplicateMediumHandler(DuplicateMediumException ex) {
        return ex.getMessage();
    }
}
