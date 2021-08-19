package de.fourzerofournotfound.rateyourstuff.rays.controllers.users;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.users.UserDto;
import de.fourzerofournotfound.rateyourstuff.rays.errors.users.RoleNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.errors.users.UserAlreadyExistsException;
import de.fourzerofournotfound.rateyourstuff.rays.errors.users.UserNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.users.Role;
import de.fourzerofournotfound.rateyourstuff.rays.models.users.User;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.users.RoleRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.users.UserRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.users.UserSecurityService;
import de.fourzerofournotfound.rateyourstuff.rays.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final UserSecurityService userSecurityService;
    private final PageableService pageableService;
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final RoleRepository roleRepository;

    @Autowired
    public UserController(UserRepository repository, UserService userService, UserSecurityService userSecurityService, PageableService pageableService, RoleRepository roleRepository) {
        this.userRepository = repository;
        this.userService = userService;
        this.userSecurityService = userSecurityService;
        this.pageableService = pageableService;
        this.roleRepository = roleRepository;
    }


    /**
     * Method returns all users from database
     *
     * @param page    the current page (optional)
     * @param size    the Number of items per Page
     * @param orderBy the attributed that should be ordered
     * @param order   the order (asc, desc)
     * @return a list of UserDTOs
     */
    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/all")
    ResponseEntity<List<UserDto>> getAll(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "50") int size,
                                         @RequestParam(defaultValue = "") String orderBy,
                                         @RequestParam(defaultValue = "asc") String order
    ) {
        Pageable pageable = pageableService.createPageable(orderBy, order, page, size);
        List<User> users = this.userRepository.findAll(pageable).getContent();

        return ResponseEntity.ok(
                users.stream()
                        .map(userService::convertToDto)
                        .collect(Collectors.toList())
        );
    }

    /**
     * Method returns a User by a given Id
     *
     * @param id of the user that should be returned
     * @return the found user
     * @throws UserNotFoundException if there is no user with given id i n database
     */
    @PreAuthorize("hasAuthority('User')")
    @GetMapping("/id={id}")
    ResponseEntity<UserDto> getById(@PathVariable Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return (ResponseEntity.ok(userService.convertToDto(user.get())));
        }
        throw new UserNotFoundException("No " + User.class.getSimpleName() + " found for id " + id);
    }

    /**
     * Method checks if a user name is already taken in database
     *
     * @param userName given user name which should be checked
     * @return true if the name isn´t already used by another user
     * @throws UserAlreadyExistsException if user name is already taken by another user
     */
    @GetMapping("/check/is={userName}")
    ResponseEntity<Boolean> getUsername(@PathVariable String userName) throws UserAlreadyExistsException {
        Optional<User> user = userRepository.findByUserNameIgnoreCase(userName);
        if (user.isPresent()) {
            throw new UserAlreadyExistsException(User.class.getSimpleName() + " tried to sign up with already taken userName: @" + userName);
        } else {
            return ResponseEntity.ok(true);
        }
    }

    /**
     * Method is used to add new users to the database
     *
     * @param user is a given user object from the web-api (sign up form)
     * @return User-DTO of the stored user object
     */
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    ResponseEntity<UserDto> add(@RequestBody User user) {
        userService.setRoleId(user);
        userSecurityService.hashPasswordOfSignUp(user);
        User savedUser = this.userRepository.save(user);
        logger.info("Added " + User.class.getSimpleName() + " with id " + savedUser.getId());
        return ResponseEntity.ok(userService.convertToDto(savedUser));
    }

    /**
     * Method is used to update an chosen user entry in database
     *
     * @param user is a given user object from the web-api (changeUserData)
     * @return User-DTO of the stored user object
     * @throws UserNotFoundException if user does not exists in database
     */
    @PreAuthorize("hasAuthority('User')")
    @PutMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<UserDto> update(@RequestBody User user) throws UserNotFoundException {
        Optional<User> potentialUser = userRepository.findUserById(user.getId());
        if (potentialUser.isPresent()) {
            potentialUser.get().setFirstName(user.getFirstName());
            potentialUser.get().setLastName(user.getLastName());
            User savedUser = userRepository.save(potentialUser.get());
            logger.info("Updated " + User.class.getSimpleName() + " with id " + user.getId());
            return ResponseEntity.ok(this.userService.convertToDto(savedUser));
        }
        throw new UserNotFoundException(User.class.getSimpleName() + " not found");
    }

    /**
     * Method is used to update the role of a given user in the database
     *
     * @param user object given from form of the frontend
     * @return User-DTO of updated and stored database entry
     * @throws RoleNotFoundException if the given role isn´t a valid role in database
     * @throws UserNotFoundException if the given user isn´t a valid user in database
     */
    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping(path = "/roleUpdate", consumes = "application/json", produces = "application/json")
    ResponseEntity<UserDto> roleUpdate(@RequestBody User user) throws RoleNotFoundException, UserNotFoundException {
        Optional<User> potentialUser = userRepository.findUserById(user.getId());
        if (potentialUser.isPresent()) {
            Optional<Role> role = roleRepository.findById(user.getRoleMappingId());
            if (role.isPresent()) {
                potentialUser.get().setRole(role.get());
                User savedUser = userRepository.save(potentialUser.get());
                logger.info("Updated " + User.class.getSimpleName() + " with id " + savedUser.getId());
                return ResponseEntity.ok(this.userService.convertToDto(savedUser));
            }
            throw new RoleNotFoundException("Role not found");
        }
        throw new UserNotFoundException("User not found");
    }

    /**
     * Method is used to update the isEnabled state of a given user in the database
     *
     * @param user object given from form of the frontend
     * @return User-DTO of updated and stored database entry
     * @throws UserNotFoundException if the given user isn´t a valid user in database
     */
    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping(path = "/isEnabledUpdate", consumes = "application/json", produces = "application/json")
    ResponseEntity<UserDto> isEnabledUpdate(@RequestBody User user) throws UserNotFoundException {
        Optional<User> potentialUser = userRepository.findUserById(user.getId());
        if (potentialUser.isPresent()) {
            potentialUser.get().getLogin().setIsEnabled(user.getLogin().getIsEnabled());
            User savedUser = userRepository.save(potentialUser.get());
            return ResponseEntity.ok(this.userService.convertToDto(savedUser));
        }
        throw new UserNotFoundException("User not found");
    }

    /**
     * Method is used to delete a user
     *
     * @param id of the chosen user to be deleted
     */
    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        this.userRepository.deleteById(id);
    }
}
