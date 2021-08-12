package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.EpisodeNotFoundException;
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
    String episodeNotFoundHandler(EpisodeNotFoundException ex) {
        return ex.getMessage();
    }
}
