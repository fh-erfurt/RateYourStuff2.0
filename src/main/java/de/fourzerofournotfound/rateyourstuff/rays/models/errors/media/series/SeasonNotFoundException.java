package de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.series;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.series.EpisodeController;
import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.series.SeasonController;

/**
 * SeasonNotFoundException
 * <p>Signals that an attempt to get a certain season from the database has failed.</p>
 * <p>This Exception will be thrown by the {@link SeasonController} and
 * the {@link EpisodeController EpisodeController}</p>
 *
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
