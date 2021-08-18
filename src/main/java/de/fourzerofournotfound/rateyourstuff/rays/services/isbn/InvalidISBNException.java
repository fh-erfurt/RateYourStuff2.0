package de.fourzerofournotfound.rateyourstuff.rays.services.isbn;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.books.BookController;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.books.isbn.ISBN10;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.books.isbn.ISBN13;

/**
 * DuplicateMediumException
 * <p>Signals that an attempt to create a medium that already exists.</p>
 * <p>This Exception will be thrown by:</p>
 * <ul>
 *     <li>{@link BookController BookController}</li>
 *     <li>{@link ISBN10 ISBN10}</li>
 *     <li>{@link ISBN13 ISBN13}</li>
 * <ul>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */

public class InvalidISBNException extends Exception {
    public InvalidISBNException() {
        super();
    }

    public InvalidISBNException(String exceptionMessage) {
        super(exceptionMessage);
    }
}