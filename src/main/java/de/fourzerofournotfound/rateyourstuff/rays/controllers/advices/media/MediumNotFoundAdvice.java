package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices.media;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.collections.CollectionController;
import de.fourzerofournotfound.rateyourstuff.rays.errors.media.MediumNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p>This Advice is used by the {@link CollectionController CollectionController}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@ControllerAdvice
public class MediumNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(MediumNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String mediumNotFoundHandler(MediumNotFoundException ex) {
        return ex.getMessage();
    }
}
