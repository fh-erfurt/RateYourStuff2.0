package de.fourzerofournotfound.rateyourstuff.rays.services.users;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.users.UserController;
import de.fourzerofournotfound.rateyourstuff.rays.models.users.Login;
import de.fourzerofournotfound.rateyourstuff.rays.models.users.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**
 * <p>This Service provides methods to the following classes:</p>
 * {@link UserController UserController}
 * {@link LoginService LoginService}
 * {@link UserSecurityService UserSecurityService}
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */

@Service("uss")
public class UserSecurityService {

    private static final String passwordSalt = BCrypt.gensalt();

    /**
     * Method receives User and hashes clear password string to hash from User object
     *
     * @param user the user that should get a hashed password
     */
    public void hashPasswordOfSignUp(User user) {
        user.getLogin().setPasswordHash(BCrypt.hashpw(user.getLogin().getPasswordHash(), passwordSalt));
    }

    /**
     * Method receives Login Object and hashes password from clear password string
     *
     * @param login the login that has the clear password
     */
    public void hashPasswordOfLogin(Login login) {
        login.setPasswordHash(BCrypt.hashpw(login.getPasswordHash(), passwordSalt));
    }

    /**
     * Method to compares given password with password from database
     *
     * @param password from api
     * @param user     from api
     * @return true if given password is equal to stored password
     */
    public boolean isValidPassword(String password, User user) {
        return BCrypt.checkpw(password, user.getLogin().getPasswordHash());
    }

    public void changePassword(String currentPassword, String newPassword, User user) {
        if (isValidPassword(currentPassword, user)) {
            user.getLogin().setPasswordHash(BCrypt.hashpw(newPassword, passwordSalt));
        }
    }
}


