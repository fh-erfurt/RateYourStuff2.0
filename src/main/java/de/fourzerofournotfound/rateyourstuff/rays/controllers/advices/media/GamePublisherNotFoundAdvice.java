package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.GamePublisherNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GamePublisherNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(GamePublisherNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String gamePublisherNotFoundHandler(GamePublisherNotFoundException ex) {
        return ex.getMessage();
    }
}
