package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices.media.series;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.series.SeriesController;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.series.EpisodeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p>This Advice is used by the {@link SeriesController SeriesController}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@ControllerAdvice
public class EpisodeNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(EpisodeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String episodeNotFoundHandler(EpisodeNotFoundException ex) {
        return ex.getMessage();
    }
}
