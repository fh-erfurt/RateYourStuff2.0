package de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.series;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.series.EpisodeController;
import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.series.SeriesController;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.MediumNotFoundException;

/**
 * <p>Signals that an attempt to get a certain series from the database has failed.</p>
 * <p>This Exception will be thrown by the {@link SeriesController SeriesController} and the
 * {@link EpisodeController EpisodeController}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
public class SeriesNotFoundException extends MediumNotFoundException {
    public SeriesNotFoundException(String message) {
        super(message);
    }
}
