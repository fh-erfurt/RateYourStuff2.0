package de.fourzerofournotfound.rateyourstuff.rays.controllers.advices.media;

import de.fourzerofournotfound.rateyourstuff.rays.services.isbn.InvalidISBNException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ISBNInvalidAdvice {
        @ResponseBody
        @ExceptionHandler(InvalidISBNException.class)
        @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
        String isbnInvalidHandler(InvalidISBNException ex) {
            return ex.getMessage();
        }
    }

