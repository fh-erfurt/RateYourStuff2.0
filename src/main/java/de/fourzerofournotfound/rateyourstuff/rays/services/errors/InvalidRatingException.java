package de.fourzerofournotfound.rateyourstuff.rays.services.errors;

public class InvalidRatingException extends Exception{
    public InvalidRatingException() {
        super();
    }

    public InvalidRatingException(String message) {
        super(message);
    }
}
