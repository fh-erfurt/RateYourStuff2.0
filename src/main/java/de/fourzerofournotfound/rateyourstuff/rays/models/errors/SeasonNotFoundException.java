package de.fourzerofournotfound.rateyourstuff.rays.models.errors;

public class SeasonNotFoundException extends Exception{
    public SeasonNotFoundException() {
        super();
    }

    public SeasonNotFoundException(String message) {
        super(message);
    }
}
