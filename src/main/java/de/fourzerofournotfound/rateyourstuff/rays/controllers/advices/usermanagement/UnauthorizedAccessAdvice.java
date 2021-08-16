package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices.usermanagement;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UnauthorizedAccessAdvice {
    @ResponseBody
    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    String unauthorizedAccessHandler(AuthenticationCredentialsNotFoundException ex) {return ex.getMessage();}
}
