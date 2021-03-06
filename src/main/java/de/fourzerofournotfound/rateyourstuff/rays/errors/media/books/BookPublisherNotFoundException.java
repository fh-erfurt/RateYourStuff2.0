package de.fourzerofournotfound.rateyourstuff.rays.errors.media.books;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.books.BookPublisherController;

/**
 * <p>Signals that an attempt to get a certain publisher from the database has failed.</p>
 * <p>This Exception will be thrown by the {@link BookPublisherController BookPublisherController}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
public class BookPublisherNotFoundException extends Exception {
    public BookPublisherNotFoundException(String message) {
        super(message);
    }
}
