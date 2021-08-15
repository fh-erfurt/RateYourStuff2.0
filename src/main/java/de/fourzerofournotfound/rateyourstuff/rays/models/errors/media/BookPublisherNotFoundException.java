package de.fourzerofournotfound.rateyourstuff.rays.models.errors.media;

/**
 * <h1>Book Publisher Not Found Exception</h1>
 * <p>Signals that an attempt to get a certain publisher from the database has failed.</p>
 * <p>This Exception will be thrown by the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.BookPublisherController BookPublisherController}</p>
 */
public class BookPublisherNotFoundException extends Throwable {
    public BookPublisherNotFoundException(String message) {
        super(message);
    }
}
