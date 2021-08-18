package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices.media.series;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.series.SeasonController;
import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.series.SeriesController;
import de.fourzerofournotfound.rateyourstuff.rays.errors.media.series.SeriesNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p>This Advice is used by the {@link SeriesController SeriesController} and
 * the {@link SeasonController SeasonController}
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@ControllerAdvice
public class SeriesNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(SeriesNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String seriesNotFoundHandler(SeriesNotFoundException ex) {
        return ex.getMessage();
    }
}
