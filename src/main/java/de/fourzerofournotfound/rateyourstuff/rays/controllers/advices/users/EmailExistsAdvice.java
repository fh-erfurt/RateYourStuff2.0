package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices.users;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.users.LoginController;
import de.fourzerofournotfound.rateyourstuff.rays.errors.users.EmailAlreadyExistsException;
import de.fourzerofournotfound.rateyourstuff.rays.services.users.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p>This Advice is used by the following classes and handles the response of the HttpStatus in the browser
 * if given email is already taken. The browser will receive a Status 418</p>
 * <li>{@link LoginController LoginController}</li>
 * <li>{@link LoginService LoginService}</li>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */

@ControllerAdvice
public class EmailExistsAdvice {
    @ResponseBody
    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    String emailExistsHandler(EmailAlreadyExistsException ex) {
        return ex.getMessage();
    }
}
