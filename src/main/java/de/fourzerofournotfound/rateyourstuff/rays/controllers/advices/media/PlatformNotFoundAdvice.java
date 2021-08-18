package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.PlatformNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * PlatformNotFoundAdvice
 * <p>This Advice is used by the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.PlatformController PlatformController}
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
