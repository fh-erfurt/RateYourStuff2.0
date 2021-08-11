package de.fourzerofournotfound.rateyourstuff.rays.services;

import de.fourzerofournotfound.rateyourstuff.rays.models.Login;
import de.fourzerofournotfound.rateyourstuff.rays.models.LoginRole;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.LoginRoleRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.InvalidLoginRoleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("lrs")
public class LoginRoleService {
    private final LoginRoleRepository repository;

    @Autowired
    public LoginRoleService(LoginRoleRepository loginRoleRepository) {
        this.repository = loginRoleRepository;
    }

    public LoginRole loginRoleValidator(Optional<LoginRole> potentialLoginRole) throws InvalidLoginRoleException {
        if(potentialLoginRole.isPresent()){
            return potentialLoginRole.get();
        } else {
            throw new InvalidLoginRoleException("Given Login Role isn´t valid Login Role");
        }
    }
}
