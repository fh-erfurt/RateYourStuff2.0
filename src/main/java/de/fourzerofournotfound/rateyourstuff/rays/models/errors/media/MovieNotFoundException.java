package de.fourzerofournotfound.rateyourstuff.rays.models.errors.media;

public class MovieNotFoundException extends Exception{
    public MovieNotFoundException(String message) {
        super(message);
    }
}
