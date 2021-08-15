package de.fourzerofournotfound.rateyourstuff.rays.services.isbn;

/**
 * DuplicateMediumException
 * <p>Signals that an attempt to create a medium that already exists.</p>
 * <p>This Exception will be thrown by:</p>
 * <ul>
 *     <li>{@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.BookController BookController}</li>
 *     <li>{@link ISBN10 ISBN10}</li>
 *     <li>{@link ISBN13 ISBN13}</li>
 * <ul>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */

public class InvalidISBNException extends Exception{
    public InvalidISBNException() {
        super();
    }

    public InvalidISBNException(String exceptionMessage) {
        super(exceptionMessage);
    }
}