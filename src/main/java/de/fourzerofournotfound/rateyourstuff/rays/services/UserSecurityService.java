package de.fourzerofournotfound.rateyourstuff.rays.services;

import de.fourzerofournotfound.rateyourstuff.rays.models.Login;
import de.fourzerofournotfound.rateyourstuff.rays.models.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service("uss")
public class UserSecurityService {
    public void hashPasswordOfSignUp(User user)
    {
        String passwordSalt = BCrypt.gensalt();
        user.getLogin().setPasswordHash(BCrypt.hashpw(user.getLogin().getPasswordHash(), passwordSalt));
    }
}
