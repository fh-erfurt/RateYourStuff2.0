package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices;

import de.fourzerofournotfound.rateyourstuff.rays.models.errors.EpisodeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EpisodeNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(EpisodeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String personNotFoundHandler(EpisodeNotFoundException ex) {
        return ex.getMessage();
    }
}
