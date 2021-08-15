package de.fourzerofournotfound.rateyourstuff.rays.models.errors.media;

/**
 * <h1>SeriesNotFoundException</h1>
 * <p>Signals that an attempt to get a certain series from the database has failed.</p>
 * <p>This Exception will be thrown by the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.SeriesController SeriesController} and the
 * {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.EpisodeController EpisodeController}</p>
 */
public class SeriesNotFoundException extends Exception{
    public SeriesNotFoundException(String message) {
        super(message);
    }
}
