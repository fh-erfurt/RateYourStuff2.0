package de.fourzerofournotfound.rateyourstuff.rays.controllers.users;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.users.UserDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.users.Role;
import de.fourzerofournotfound.rateyourstuff.rays.models.users.User;
import de.fourzerofournotfound.rateyourstuff.rays.errors.users.UserNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.users.RoleRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.users.UserRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.users.UserSecurityService;
import de.fourzerofournotfound.rateyourstuff.rays.services.users.UserService;
import de.fourzerofournotfound.rateyourstuff.rays.errors.users.UserAlreadyExistsException;
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

    @PreAuthorize("hasAuthority('User')")
    @GetMapping("/id={id}")
    ResponseEntity<UserDto> getById(@PathVariable Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return (ResponseEntity.ok(userService.convertToDto(user.get())));
        }
        throw new UserNotFoundException("No " + User.class.getSimpleName() + " found for id " + id);
    }

    @GetMapping("/check/is={userName}")
    ResponseEntity<Boolean> getUsername(@PathVariable String userName) throws UserAlreadyExistsException {
        Optional<User> user = userRepository.findByUserNameIgnoreCase(userName);
        if (user.isPresent()) {
            throw new UserAlreadyExistsException(User.class.getSimpleName() + " tried to sign up with already taken userName: @" + userName);
        } else {
            return ResponseEntity.ok(true);
        }
    }

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    ResponseEntity<UserDto> add(@RequestBody User user) {
        userService.setRoleId(user);
        userSecurityService.hashPasswordOfSignUp(user);
        User savedUser = this.userRepository.save(user);
        logger.info("Added " + User.class.getSimpleName() + " with id " + savedUser.getId());
        return ResponseEntity.ok(userService.convertToDto(savedUser));
    }

    @PreAuthorize("hasAuthority('User')")
    @PutMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<UserDto> update(@RequestBody User user) throws UserNotFoundException, UserAlreadyExistsException {
        Optional<User> potentialUser = userRepository.findUserById(user.getId());
        if (potentialUser.isPresent()) {
            userService.manageUpdatePersistence(user, potentialUser);
            User savedUser = userRepository.save(potentialUser.get());
            logger.info("Updated " + User.class.getSimpleName() + " with id " + user.getId());
            return ResponseEntity.ok(this.userService.convertToDto(savedUser));
        }
        throw new UserNotFoundException(User.class.getSimpleName() + " not found");
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping(path="/customUpdate", consumes = "application/json", produces = "application/json")
    ResponseEntity<UserDto> customUpdate(@RequestBody User user) throws Exception {
        Optional<User> potentialUser = userRepository.findUserById(user.getId());
        if(potentialUser.isPresent()) {
            Optional<Role> role = roleRepository.findById(user.getRoleMappingId());
            if(role.isPresent()){
                potentialUser.get().setRole(role.get());
                User savedUser = userRepository.save(potentialUser.get());
                logger.info("Updated " + User.class.getSimpleName() + " with id " + savedUser.getId());
                return ResponseEntity.ok(this.userService.convertToDto(savedUser));
            }
            throw new Exception();
        }
        throw new Exception();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping(path="/isEnabledUpdate", consumes = "application/json", produces = "application/json")
    ResponseEntity<UserDto> isEnabledUpdate(@RequestBody User user) throws Exception {
        Optional<User> potentialUser = userRepository.findUserById(user.getId());
        if(potentialUser.isPresent()) {
            potentialUser.get().getLogin().setIsEnabled(user.getLogin().getIsEnabled());
            User savedUser = userRepository.save(potentialUser.get());
            return ResponseEntity.ok(this.userService.convertToDto(savedUser));
        }
        throw new Exception();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        this.userRepository.deleteById(id);
    }
}
