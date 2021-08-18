package de.fourzerofournotfound.rateyourstuff.rays.services.errors;


import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.games.GameController;
import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.movies.MovieController;
import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.series.EpisodeController;
import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.series.SeasonController;
import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.series.SeriesController;

/**
 * DuplicateMediumException
 * <p>Signals that an attempt to create a medium that already exists.</p>
 * <p>This Exception will be thrown by: </p><ul>
 * <li>{@link EpisodeController EpisodeController}</li>
 * <li>{@link GameController GameController}</li>
 * <li>{@link MovieController MovieController}</li>
 * <li>{@link SeasonController SeasonController}</li>
 * <li>{@link SeriesController SeriesController}</li>
 * </ul>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
public class DuplicateMediumException extends Exception {
    public DuplicateMediumException(String message) {
        super(message);
    }
}
