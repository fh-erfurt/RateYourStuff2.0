package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices;

import de.fourzerofournotfound.rateyourstuff.rays.models.errors.InvalidSeriesException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InvalidSeriesAdvice {

        @ResponseBody
        @ExceptionHandler(InvalidSeriesException.class)
        @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
        String invalidSeriesHandler(InvalidSeriesException ex) {
            return ex.getMessage();
        }

}
