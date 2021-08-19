package de.fourzerofournotfound.rateyourstuff.rays.services.users;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.users.UserDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.users.Login;
import de.fourzerofournotfound.rateyourstuff.rays.models.users.Role;
import de.fourzerofournotfound.rateyourstuff.rays.models.users.User;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.users.LoginRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.users.RoleRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.users.UserRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.users.RoleRepository;
import de.fourzerofournotfound.rateyourstuff.rays.errors.users.InvalidLoginException;
import de.fourzerofournotfound.rateyourstuff.rays.errors.users.InvalidUserException;
import de.fourzerofournotfound.rateyourstuff.rays.errors.users.UserAlreadyExistsException;
import org.jboss.logging.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("us")
public class UserService {
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    public UserService(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }


    /**
     * Is setting the RoleId of a User Object
     *
     * @param user given user object from Web-API
     */
    public void setRoleId(User user) {
        Optional<Role> potentialRole = roleRepository.findRoleByRoleNameIgnoreCase(user.getRole().getRoleName());
        System.out.println("PotentialRole: " + potentialRole.get().getRoleName());
        if (potentialRole.isPresent()) {
            user.getRole().setId(potentialRole.get().getId());
        }
    }

    public UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}
