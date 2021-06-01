package de.fourzerofournotfound.rateyourstuff.rays.models.errors;

public class MovieNotFoundException extends Exception{
    public MovieNotFoundException() {
        super();
    }

    public MovieNotFoundException(String message) {
        super(message);
    }
}
