package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices.media.games;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.games.PlatformController;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.games.PlatformNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * PlatformNotFoundAdvice
 * <p>This Advice is used by the {@link PlatformController PlatformController}
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@ControllerAdvice
public class PlatformNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(PlatformNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String platformNotFoundHandler(PlatformNotFoundException ex) {
        return ex.getMessage();
    }
}
