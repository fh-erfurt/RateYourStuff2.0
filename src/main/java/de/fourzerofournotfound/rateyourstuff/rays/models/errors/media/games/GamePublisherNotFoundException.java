package de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.games;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.games.GamePublisherController;

/**
 * <p>Signals that an attempt to get a certain gamePublisher from the database has failed.</p>
 * <p>This Exception will be thrown by the {@link GamePublisherController GamePublisherController}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
public class GamePublisherNotFoundException extends Exception {
    public GamePublisherNotFoundException(String message) {
        super(message);
    }
}
