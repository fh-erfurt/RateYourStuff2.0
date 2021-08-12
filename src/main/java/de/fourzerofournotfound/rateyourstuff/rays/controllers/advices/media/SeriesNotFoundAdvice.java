package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.SeriesNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SeriesNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(SeriesNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String seriesNotFoundHandler(SeriesNotFoundException ex) {
        return ex.getMessage();
    }
}
