package de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.series;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.series.EpisodeController;

/**
 * EpisodeNotFoundException
 * <p>Signals that an attempt to get a certain episode from the database has failed.</p>
 * <p>This Exception will be thrown by the {@link EpisodeController EpisodeController}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
public class EpisodeNotFoundException extends Exception {
    public EpisodeNotFoundException(String message) {
        super(message);
    }
}
