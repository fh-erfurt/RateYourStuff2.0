package de.fourzerofournotfound.rateyourstuff.rays.errors.users;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.users.LoginController;
import de.fourzerofournotfound.rateyourstuff.rays.services.users.UserService;

/**
 * <p>This Exception will be thrown if a given Login is not valid in the database
 * and will be thrown in the following classes:</p>
 * {@link LoginController LoginController}
 * {@link UserService Userservice}
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */

public class InvalidLoginException extends Exception {
    public InvalidLoginException(String message) {
        super(message);
    }
}
