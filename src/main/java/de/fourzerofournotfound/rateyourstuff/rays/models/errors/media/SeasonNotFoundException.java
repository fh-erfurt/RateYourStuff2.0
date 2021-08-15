package de.fourzerofournotfound.rateyourstuff.rays.models.errors.media;

/**
 * SeasonNotFoundException
 * <p>Signals that an attempt to get a certain season from the database has failed.</p>
 * <p>This Exception will be thrown by the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.SeasonController} and
 * the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.EpisodeController EpisodeController}</p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
public class SeasonNotFoundException extends Exception {
    public SeasonNotFoundException(String message) {
        super(message);
    }
}
