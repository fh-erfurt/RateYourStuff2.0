package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.UserDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.User;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.UserNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Movie;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.UserRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.InvalidUserException;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import de.fourzerofournotfound.rateyourstuff.rays.services.UserSecurityService;
import de.fourzerofournotfound.rateyourstuff.rays.services.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final UserSecurityService userSecurityService;
    private final PageableService pageableService;

    @Autowired
    public UserController(UserRepository repository, UserService userService, UserSecurityService userSecurityService, PageableService pageableService) {
        this.userRepository = repository;
        this.userService = userService;
        this.userSecurityService = userSecurityService;
        this.pageableService = pageableService;
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
    ResponseEntity<User> getById(@PathVariable Long id) throws UserNotFoundException {
        return ResponseEntity.ok((this.userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("No User found for given id"))));
    }

    @GetMapping("/check/is={userName}")
    ResponseEntity<Boolean> getUsername(@PathVariable String userName) throws UserAlreadyExistsException {
        Optional<User> user = userRepository.findByUserNameIgnoreCase(userName);
        if(user.isPresent()){
            throw new UserAlreadyExistsException("User tried to sign up with already taken userName: @" + userName);
        } else {
            return ResponseEntity.ok(true);
        }
    }

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    ResponseEntity<User> add(@RequestBody User user) {
        userService.setRoleId(user);
        userSecurityService.hashPasswordOfSignUp(user);
        return ResponseEntity.ok(this.userRepository.save(user));
    }

    @PreAuthorize("hasAuthority('User')")
    @PutMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<User> update(@RequestBody User user) throws InvalidUserException {
        userService.addReferencesToUser(user);
        return ResponseEntity.ok(this.userRepository.save(user));
    }

    @PreAuthorize("hasAnyAuthority()")
    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {this.userRepository.deleteById(id);}
}
