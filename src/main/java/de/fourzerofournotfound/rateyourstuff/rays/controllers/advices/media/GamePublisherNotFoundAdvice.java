package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices.media;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.games.GameController;
import de.fourzerofournotfound.rateyourstuff.rays.errors.media.games.GamePublisherNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p>This Advice is used by the {@link GameController GameController}
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@ControllerAdvice
public class GamePublisherNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(GamePublisherNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String gamePublisherNotFoundHandler(GamePublisherNotFoundException ex) {
        return ex.getMessage();
    }
}
