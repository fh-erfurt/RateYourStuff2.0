package de.fourzerofournotfound.rateyourstuff.rays.models.errors.media;

/**
 * <h1>GamePublisherNotFoundException</h1>
 * <p>Signals that an attempt to get a certain gamePublisher from the database has failed.</p>
 * <p>This Exception will be thrown by the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.GamePublisherController GamePublisherController}</p>
 */
public class GamePublisherNotFoundException extends Throwable {
    public GamePublisherNotFoundException(String message) {
        super(message);
    }
}
