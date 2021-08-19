package de.fourzerofournotfound.rateyourstuff.rays.errors.users;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.users.LoginController;

/**
 * <p>This Exception will be thrown if a given Login is not found in the database
 * and will be thrown in the following classes:</p>
 * {@link LoginController LoginController}
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */


public class LoginNotFoundException extends Exception {
    public LoginNotFoundException(String message) {
        super(message);
    }
}
