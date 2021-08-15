package de.fourzerofournotfound.rateyourstuff.rays.services.errors;


/**
 * <h1>DuplicateMediumException</h1>
 * <p>Signals that an attempt to create a medium that already exists.</p>
 * <p>This Exception will be thrown by: <ul>
 *     <li>{@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.EpisodeController EpisodeController}</li>
 *     <li>{@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.GameController GameController}</li>
 *     <li>{@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.MovieController MovieController}</li>
 *     <li>{@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.SeasonController SeasonController}</li>
 *     <li>{@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.SeriesController SeriesController}</li>
 * </ul></p>
 */
public class DuplicateMediumException extends Exception{
    public DuplicateMediumException(String message) {
        super(message);
    }
}
