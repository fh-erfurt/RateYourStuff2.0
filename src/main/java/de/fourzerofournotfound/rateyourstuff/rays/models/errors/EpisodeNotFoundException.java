package de.fourzerofournotfound.rateyourstuff.rays.models.errors;

public class EpisodeNotFoundException extends Exception{

    public EpisodeNotFoundException() {
        super();
    }

    public EpisodeNotFoundException(String message) {
        super(message);
    }
}
