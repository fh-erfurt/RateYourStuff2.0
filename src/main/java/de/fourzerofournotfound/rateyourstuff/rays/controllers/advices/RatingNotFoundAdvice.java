package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices;

import de.fourzerofournotfound.rateyourstuff.rays.models.errors.CommentNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.RatingNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RatingNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(RatingNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String personNotFoundHandler(RatingNotFoundException ex) {
        return ex.getMessage();
    }
}
