package de.fourzerofournotfound.rateyourstuff.rays.models.errors.media;

/**
 * <h1>MovieNotFoundException</h1>
 * <p>Signals that an attempt to get a certain movie from the database has failed.</p>
 * <p>This Exception will be thrown by the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.MovieController MovieController}</p>
 */
public class MovieNotFoundException extends Exception{
    public MovieNotFoundException(String message) {
        super(message);
    }
}
