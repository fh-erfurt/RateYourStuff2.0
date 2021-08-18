package de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.movies;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.movies.MovieController;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.MediumNotFoundException;

/**
 * <p>Signals that an attempt to get a certain movie from the database has failed.</p>
 * <p>This Exception will be thrown by the {@link MovieController MovieController}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
public class MovieNotFoundException extends MediumNotFoundException {
    public MovieNotFoundException(String message) {
        super(message);
    }
}
