package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices.users;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p>This advice handles the response of the HttpStatus to the Browser if the Access is denied for the current request
 * cause of missing rights. The Browser will receive a status 403</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */

@ControllerAdvice
public class AccessDeniedAdvice {
    @ResponseBody
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String accessDeniedHandler(AccessDeniedException ex) {
        return ex.getMessage();
    }
}
