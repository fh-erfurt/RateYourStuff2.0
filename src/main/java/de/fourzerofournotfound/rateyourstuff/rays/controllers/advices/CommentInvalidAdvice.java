package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices;

import de.fourzerofournotfound.rateyourstuff.rays.errors.InvalidCommentException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CommentInvalidAdvice {
    @ResponseBody
    @ExceptionHandler(InvalidCommentException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    String commentInvalidHandler(InvalidCommentException ex) {
        return ex.getMessage();
    }
}