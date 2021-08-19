package de.fourzerofournotfound.rateyourstuff.rays.errors.users;


import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.collections.CollectionController;
import de.fourzerofournotfound.rateyourstuff.rays.controllers.users.UserController;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.collections.CollectionService;

/**
 * <p>This Exception will be thrown if a given User was not found in the database
 * and will be thrown in the following classes:</p>
 * {@link CollectionController CollectionController}
 * {@link CollectionService CollectionService}
 * {@link UserController UserController}
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */


public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
