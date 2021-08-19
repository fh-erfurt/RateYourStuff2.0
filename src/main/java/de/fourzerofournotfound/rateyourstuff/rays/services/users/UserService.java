package de.fourzerofournotfound.rateyourstuff.rays.services.users;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.users.SecurityController;
import de.fourzerofournotfound.rateyourstuff.rays.controllers.users.UserController;
import de.fourzerofournotfound.rateyourstuff.rays.dtos.users.UserDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.users.Role;
import de.fourzerofournotfound.rateyourstuff.rays.models.users.User;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.users.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>This Service provides methods to the following classes:</p>
 * {@link UserController UserController}
 * {@link SecurityController SecurityController}
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */

@Service("us")
public class UserService {
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

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

    /**
     * Method converts a given User object to a custom User-DTO
     * @param user to be converted
     * @return User-DTO
     */
    public UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}
