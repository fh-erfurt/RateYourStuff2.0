package de.fourzerofournotfound.rateyourstuff.rays.models.errors.media;

/**
 * CollectionNotFoundException
 * <p>Signals that an attempt to get a certain collection from the database has failed.</p>
 * <p>The Exception will be thrown by the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.CollectionController CollectionController}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
public class CollectionNotFoundException extends Exception {
    public CollectionNotFoundException(String message) {
        super(message);
    }
}
