package de.fourzerofournotfound.rateyourstuff.rays.models.errors.media;

/**
 * <h1>PlatformNotFoundException</h1>
 * <p>Signals that an attempt to get a certain platform from the database has failed.</p>
 * <p>This Exception will be thrown by the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.PlatformController PlatformController}</p>
 */
public class PlatformNotFoundException extends Throwable {
    public PlatformNotFoundException(String message) {
        super(message);
    }
}
