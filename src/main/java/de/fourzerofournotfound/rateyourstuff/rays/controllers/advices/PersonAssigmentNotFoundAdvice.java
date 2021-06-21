package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices;

import de.fourzerofournotfound.rateyourstuff.rays.models.errors.PersonAssigmentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PersonAssigmentNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(PersonAssigmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String personAssignmentNotFoundHandler(PersonAssigmentNotFoundException ex) {
        return ex.getMessage();
    }
}
