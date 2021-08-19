package de.fourzerofournotfound.rateyourstuff.rays.errors.users;

import de.fourzerofournotfound.rateyourstuff.rays.services.users.UserService;

/**
 * <p>This Exception will be thrown if a given User is not valid in the database
 * and will be thrown in the following class:</p>
 * {@link UserService Userservice}
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */


public class InvalidUserException extends Exception {
    public InvalidUserException(String message) {
        super(message);
    }
}
