package de.fourzerofournotfound.rateyourstuff.rays.errors;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.RatingController;

/**
 * <p>Signals that an attempt to get a certain Rating from the database has failed.</p>
 * <p>This Exception will be thrown by the {@link RatingController RatingController}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
public class RatingNotFoundException extends Exception {
    public RatingNotFoundException(String message) {
        super(message);
    }
}
