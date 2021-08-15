package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.NetworkNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * NetworkNotFoundAdvice
 * <p>This Advice is used by the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.NetworkController NetworkController}
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@ControllerAdvice
public class NetworkNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(NetworkNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String networkNotFoundHandler(NetworkNotFoundException ex) {
        return ex.getMessage();
    }
}
