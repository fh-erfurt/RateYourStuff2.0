package de.fourzerofournotfound.rateyourstuff.rays.errors.users;


import de.fourzerofournotfound.rateyourstuff.rays.controllers.users.UserController;


/**
 * <p>This Exception will be thrown if a given Login is not valid in the database
 * and will be thrown in the following classes:</p>
 * {@link UserController LoginController}
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */


public class RoleNotFoundException extends Exception {
    public RoleNotFoundException(String message) {
        super(message);
    }
}
