package de.fourzerofournotfound.rateyourstuff.rays.errors.users;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.users.UserController;
import de.fourzerofournotfound.rateyourstuff.rays.services.users.UserService;

/**
 * <p>This Exception will be thrown if a given User already exists in the database
 * and will be thrown in the following classes:</p>
 * {@link UserController UserController}
 * {@link UserService Userservice}
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */


public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
