package de.fourzerofournotfound.rateyourstuff.rays.models.errors.media;

/**
 * <h1>BookNotFoundException</h1>
 * <p>Signals that an attempt to get a certain publisher from the database has failed.</p>
 * <p>The Exception will be thrown by the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.BookController BookController}.</p>
 */
public class BookNotFoundException extends Throwable {
    public BookNotFoundException(String message) {
        super(message);
    }
}
