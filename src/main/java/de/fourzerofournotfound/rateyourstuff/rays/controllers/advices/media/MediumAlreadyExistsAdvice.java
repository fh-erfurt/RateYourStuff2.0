package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.MediumAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

    @ControllerAdvice
public class MediumAlreadyExistsAdvice {

        @ResponseBody
        @ExceptionHandler(MediumAlreadyExistsException.class)
        @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
        String mediumAlreadyExistsHandler(MediumAlreadyExistsException ex) {
            return ex.getMessage();
        }

}
