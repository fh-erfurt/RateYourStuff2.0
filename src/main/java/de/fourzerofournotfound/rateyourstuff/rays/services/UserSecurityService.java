package de.fourzerofournotfound.rateyourstuff.rays.services;

import de.fourzerofournotfound.rateyourstuff.rays.models.Login;
import de.fourzerofournotfound.rateyourstuff.rays.models.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service("uss")
public class UserSecurityService {
    private static final Logger UCC_LOGGER = Logger.getLogger(UserSecurityService.class.getName());

    private static final String passwordSalt = BCrypt.gensalt();

    /**
     * Method receives User and hashes clear password string to hash from User object
     * @param user
     */
    public void hashPasswordOfSignUp(User user)
    {
        user.getLogin().setPasswordHash(BCrypt.hashpw(user.getLogin().getPasswordHash(), passwordSalt));
    }

    /**
     * Method receives Login Object and hashes password from clear password string
     * @param login
     */
    public void hashPasswordOfLogin(Login login)
    {
        login.setPasswordHash(BCrypt.hashpw(login.getPasswordHash(), passwordSalt));
    }

    /**
     * Method to compares given password with password from database
     * @param password from api
     * @param user from api
     * @return true if given password is equal to stored password
     */
    public boolean isValidPassword(String password, User user)
    {
        return BCrypt.checkpw(password, user.getLogin().getPasswordHash());
    }

    public void changePassword(String currentPassword, String newPassword, User user)
    {
        if(isValidPassword(currentPassword, user)) {
            user.getLogin().setPasswordHash(BCrypt.hashpw(newPassword, passwordSalt));
        }
    }
}


