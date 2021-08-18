package de.fourzerofournotfound.rateyourstuff.rays.models.errors.media;

/**
 * SeriesNotFoundException
 * <p>Signals that an attempt to get a certain series from the database has failed.</p>
 * <p>This Exception will be thrown by the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.SeriesController SeriesController} and the
 * {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.EpisodeController EpisodeController}</p>
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
