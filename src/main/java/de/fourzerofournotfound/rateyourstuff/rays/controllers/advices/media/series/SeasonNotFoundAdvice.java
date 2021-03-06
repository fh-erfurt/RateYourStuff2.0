package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices.media.series;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.series.EpisodeController;
import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.series.SeasonController;
import de.fourzerofournotfound.rateyourstuff.rays.errors.media.series.SeasonNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p>This Advice is used by the {@link SeasonController SeasonController}
 * and the {@link EpisodeController EpisodeController}
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@ControllerAdvice
public class SeasonNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(SeasonNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String seasonNotFoundHandler(SeasonNotFoundException ex) {
        return ex.getMessage();
    }
}
