package de.fourzerofournotfound.rateyourstuff.rays.models.errors.media;

/**
 * MediumNotFoundException
 * <p>Signals that an attempt to get a certain medium from the database has failed.</p>
 * <p>This Exception will be thrown by the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.CollectionController CollectionController}</p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
public class MediumNotFoundException extends Exception{
    public MediumNotFoundException(String message) {
        super(message);
    }
}
