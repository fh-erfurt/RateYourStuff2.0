package de.fourzerofournotfound.rateyourstuff.rays.services;

import de.fourzerofournotfound.rateyourstuff.rays.models.Login;
import de.fourzerofournotfound.rateyourstuff.rays.models.LoginRole;
import de.fourzerofournotfound.rateyourstuff.rays.models.Role;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.LoginNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.LoginRepository;
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
    private final LoginRepository loginRepository;

    @Autowired
    public LoginRoleService(LoginRoleRepository repository, RoleRepository roleRepository, LoginRepository loginRepository) {
        this.loginRoleRepository = repository;
        this.roleRepository = roleRepository;
        this.loginRepository = loginRepository;
    }

    /**
     * Check if the given login role is valid
     * @param potentialLoginRole Parameter from Web-API
     * @return If the given LoginRole is present return the loginRole-Object
     * @throws InvalidLoginRoleException if given LoginRole isn´t valid
     */
    public LoginRole loginRoleValidator(Optional<LoginRole> potentialLoginRole) throws InvalidLoginRoleException {
        if(potentialLoginRole.isPresent()){
            return potentialLoginRole.get();
        } else {
            throw new InvalidLoginRoleException("Given Login Role isn´t valid Login Role");
        }
    }

    /**
     * Given Function receives the name of role and returns the id of the database entry
     * @param RoleName given Parameter from Web-API
     * @return ID for given RoleName
     * @throws InvalidRoleException if there is no identical role name in database
     */
    public Long returnRoleId(String RoleName) throws InvalidRoleException {
        Optional<Role> potentialRole = roleRepository.findRoleByRoleName(RoleName);
        if(potentialRole.isPresent())
        {
            return potentialRole.get().getId();
        } else {
            throw new InvalidRoleException("Given Login Role don´t exists");
        }
    }

    /**
     * Is setting the RoleId with function returnRoleId and given RoleName from Web-API
     * @param loginRole given LoginRole from Web-API
     * @throws InvalidRoleException if LoginRole isn´t valid
     */
    public void setRoleId(LoginRole loginRole) throws InvalidRoleException {
        loginRole.getRole().setId(returnRoleId(loginRole.getRole().getRoleName()));
    }

    public Long returnLoginId(Login login) throws LoginNotFoundException {
        Optional<Login> potentialLogin = loginRepository.findLoginByEmailIgnoreCase(login.getEmail());
        if(potentialLogin.isPresent()){
            return potentialLogin.get().getId();
        } else {
            throw new LoginNotFoundException("Login with email " + potentialLogin.get().getEmail() + " not found in DB");
        }
    }

    public void setLoginId(LoginRole loginRole) throws LoginNotFoundException {
        loginRole.getLogin().setId(returnLoginId(loginRole.getLogin()));
    }
}
