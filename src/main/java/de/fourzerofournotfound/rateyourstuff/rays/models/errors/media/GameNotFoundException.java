package de.fourzerofournotfound.rateyourstuff.rays.models.errors.media;

/**
 * <h1>GameNotFoundException</h1>
 * <p>Signals that an attempt to get a certain game from the database has failed.</p>
 * <p>This Exception will be thrown by the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.GameController GameController}</p>
 */
public class GameNotFoundException extends Throwable {
    public GameNotFoundException(String message) {
        super(message);
    }
}
