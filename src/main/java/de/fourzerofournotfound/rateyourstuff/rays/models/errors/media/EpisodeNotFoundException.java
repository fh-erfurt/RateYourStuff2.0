package de.fourzerofournotfound.rateyourstuff.rays.models.errors.media;

/**
 * EpisodeNotFoundException
 * <p>Signals that an attempt to get a certain episode from the database has failed.</p>
 * <p>This Exception will be thrown by the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.EpisodeController EpisodeController}</p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
public class EpisodeNotFoundException extends Exception{
    public EpisodeNotFoundException(String message) {
        super(message);
    }
}
