package de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.books;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.books.BookController;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.MediumNotFoundException;

/**
 * BookNotFoundException
 * <p>Signals that an attempt to get a certain publisher from the database has failed.</p>
 * <p>The Exception will be thrown by the {@link BookController BookController}.</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
public class BookNotFoundException extends MediumNotFoundException {
    public BookNotFoundException(String message) {
        super(message);
    }
}
