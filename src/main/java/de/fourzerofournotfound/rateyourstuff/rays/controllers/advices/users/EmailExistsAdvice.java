package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices.users;

import de.fourzerofournotfound.rateyourstuff.rays.errors.users.EmailAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EmailExistsAdvice {
    @ResponseBody
    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    String emailExistsHandler(EmailAlreadyExistsException ex) {return ex.getMessage();}
}
