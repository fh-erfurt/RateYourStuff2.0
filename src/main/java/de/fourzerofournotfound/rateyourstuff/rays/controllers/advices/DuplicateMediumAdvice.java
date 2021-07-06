package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices;

import de.fourzerofournotfound.rateyourstuff.rays.models.errors.CommentNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.DuplicateMediumException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DuplicateMediumAdvice {
    @ResponseBody
    @ExceptionHandler(DuplicateMediumException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String duplicateMediumHandler(DuplicateMediumException ex) {
        return ex.getMessage();
    }
}
