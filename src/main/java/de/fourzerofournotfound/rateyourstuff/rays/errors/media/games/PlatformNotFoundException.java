package de.fourzerofournotfound.rateyourstuff.rays.errors.media.games;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.games.PlatformController;

/**
 * <p>Signals that an attempt to get a certain platform from the database has failed.</p>
 * <p>This Exception will be thrown by the {@link PlatformController PlatformController}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
public class PlatformNotFoundException extends Exception {
    public PlatformNotFoundException(String message) {
        super(message);
    }
}
