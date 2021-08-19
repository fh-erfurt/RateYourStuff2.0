package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices.users;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.users.LoginController;
import de.fourzerofournotfound.rateyourstuff.rays.errors.users.UserAlreadyExistsException;
import de.fourzerofournotfound.rateyourstuff.rays.services.users.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p>This Advice is used by the</p>
 * <li>{@link LoginController LoginController}</li>
 * <li>{@link LoginService LoginService}</li>
 */

@ControllerAdvice
public class UserExistsAdvice {
    @ResponseBody
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    String seriesNotFoundHandler(UserAlreadyExistsException ex) {
        return ex.getMessage();
    }
}
