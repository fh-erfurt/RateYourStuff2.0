package de.fourzerofournotfound.rateyourstuff.rays.models.errors;

public class RatingNotFoundException extends Exception{

    public RatingNotFoundException() {
        super();
    }

    public RatingNotFoundException(String message) {
        super(message);
    }
}
