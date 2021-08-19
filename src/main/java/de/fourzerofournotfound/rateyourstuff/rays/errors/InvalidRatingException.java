package de.fourzerofournotfound.rateyourstuff.rays.errors;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.RatingController;

/**
 * <p>Signals that an attempt to add a certain Rating to the database has failed.</p>
 * <p>This Exception will be thrown by the {@link RatingController RatingController}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
public class InvalidRatingException extends Exception {
    public InvalidRatingException(String message) {
        super(message);
    }
}
