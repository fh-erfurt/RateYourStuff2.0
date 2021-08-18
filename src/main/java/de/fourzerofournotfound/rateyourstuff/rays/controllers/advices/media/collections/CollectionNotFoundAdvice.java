package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices.media.collections;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.collections.CollectionController;
import de.fourzerofournotfound.rateyourstuff.rays.errors.media.collections.CollectionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * CollectionNotFoundAdvice
 * <p>This Advice is used by the {@link CollectionController CollectionController}
 * and the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.MediumController MediumController}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@ControllerAdvice
public class CollectionNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(CollectionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String collectionNotFoundHandler(CollectionNotFoundException ex) {
        return ex.getMessage();
    }
}
