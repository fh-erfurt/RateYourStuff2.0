package de.fourzerofournotfound.rateyourstuff.rays.models.errors;

public class GameNotFoundException extends Throwable {

    public GameNotFoundException() {
        super();
    }

    public GameNotFoundException(String message) {
        super(message);
    }
}
