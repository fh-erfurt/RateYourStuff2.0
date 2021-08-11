package de.fourzerofournotfound.rateyourstuff.rays.services;

import de.fourzerofournotfound.rateyourstuff.rays.models.Login;
import de.fourzerofournotfound.rateyourstuff.rays.models.LoginRole;
import de.fourzerofournotfound.rateyourstuff.rays.models.Role;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.LoginRoleRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.RoleRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.InvalidLoginRoleException;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.InvalidRoleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("lrs")
public class LoginRoleService {
    private final LoginRoleRepository loginRoleRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public LoginRoleService(LoginRoleRepository repository, RoleRepository roleRepository) {
        this.loginRoleRepository = repository;
        this.roleRepository = roleRepository;
    }

    public LoginRole loginRoleValidator(Optional<LoginRole> potentialLoginRole) throws InvalidLoginRoleException {
        if(potentialLoginRole.isPresent()){
            return potentialLoginRole.get();
        } else {
            throw new InvalidLoginRoleException("Given Login Role isn´t valid Login Role");
        }
    }

    public Long returnRoleId(String RoleName) throws InvalidRoleException {
        Optional<Role> potentialRole = roleRepository.findRoleByRoleName(RoleName);
        if(potentialRole.isPresent())
        {
            return potentialRole.get().getId();
        } else {
            throw new InvalidRoleException("Given Login Role don´t exists");
        }
    }

    public void setRoleId(LoginRole loginRole) throws InvalidRoleException {
        loginRole.getRole().setId(returnRoleId(loginRole.getRole().getRoleName()));
    }
}
