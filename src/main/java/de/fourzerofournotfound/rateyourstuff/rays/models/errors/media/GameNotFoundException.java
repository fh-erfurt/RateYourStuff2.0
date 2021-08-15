package de.fourzerofournotfound.rateyourstuff.rays.models.errors.media;

/**
 * GameNotFoundException
 * <p>Signals that an attempt to get a certain game from the database has failed.</p>
 * <p>This Exception will be thrown by the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.GameController GameController}</p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
public class GameNotFoundException extends MediumNotFoundException {
    public GameNotFoundException(String message) {
        super(message);
    }
}
