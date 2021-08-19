package de.fourzerofournotfound.rateyourstuff.rays.errors;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.CommentController;

/**
 * <p>Signals that an attempt to add a certain Comment to the database has failed.</p>
 * <p>This Exception will be thrown by the {@link CommentController CommentController}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
public class InvalidCommentException extends Exception {
    public InvalidCommentException(String message) {
        super(message);
    }
}
