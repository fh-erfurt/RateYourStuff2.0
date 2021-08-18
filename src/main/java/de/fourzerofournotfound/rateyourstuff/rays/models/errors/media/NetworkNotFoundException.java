package de.fourzerofournotfound.rateyourstuff.rays.models.errors.media;

/**
 * <p>Signals that an attempt to get a certain network from the database has failed.</p>
 * <p>This Exception will be thrown by the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.NetworkController NetworkController}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
public class NetworkNotFoundException extends Exception {
    public NetworkNotFoundException(String message) {
        super(message);
    }
}
