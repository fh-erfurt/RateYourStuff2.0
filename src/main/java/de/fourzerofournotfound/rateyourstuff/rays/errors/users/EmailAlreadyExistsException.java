package de.fourzerofournotfound.rateyourstuff.rays.errors.users;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.users.LoginController;
import de.fourzerofournotfound.rateyourstuff.rays.services.users.LoginService;

/**
 * <p>This Exception will be thrown if a searched Email-Address already exists in the database
 * and will be thrown in the following classes:</p>
 * {@link LoginController LoginController}
 * {@link LoginService Loginservice}
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */

public class EmailAlreadyExistsException extends Exception {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
