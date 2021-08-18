package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices.users;

import de.fourzerofournotfound.rateyourstuff.rays.errors.users.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserExistsAdvice {
    @ResponseBody
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    String seriesNotFoundHandler(UserAlreadyExistsException ex) {
        return ex.getMessage();
    }
}
