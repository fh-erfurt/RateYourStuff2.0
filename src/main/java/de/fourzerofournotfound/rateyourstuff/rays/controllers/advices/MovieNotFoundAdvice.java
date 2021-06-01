package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices;

import de.fourzerofournotfound.rateyourstuff.rays.models.errors.EpisodeNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.MovieNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MovieNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(MovieNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String personNotFoundHandler(MovieNotFoundException ex) {
        return ex.getMessage();
    }
}
