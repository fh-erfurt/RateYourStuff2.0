package de.fourzerofournotfound.rateyourstuff.rays.services;

import de.fourzerofournotfound.rateyourstuff.rays.models.Login;
import de.fourzerofournotfound.rateyourstuff.rays.models.User;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.InvalidPasswordException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service("uss")
public class UserSecurityService {
    private static final Logger UCC_LOGGER = Logger.getLogger(UserSecurityService.class.getName());

    String passwordSalt = BCrypt.gensalt();

    public void hashPasswordOfSignUp(User user)
    {

        user.getLogin().setPasswordHash(BCrypt.hashpw(user.getLogin().getPasswordHash(), passwordSalt));
    }

    public boolean isValidPassword(String password, User user)
    {
        return BCrypt.checkpw(password, user.getLogin().getPasswordHash());
    }

    public void changePassword(String currentPassword, String newPassword, User user)
    {
        if(isValidPassword(currentPassword, user))
        {
            try {
                user.getLogin().setPasswordHash(BCrypt.hashpw(newPassword, passwordSalt));
                throw new InvalidPasswordException("Incorrect password");
            } catch(InvalidPasswordException e) {
                UCC_LOGGER.warning("Error: " + e.getMessage());
            }
        }
    }
}


