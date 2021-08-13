package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.models.User;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.UserNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.UserRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.InvalidRoleException;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.InvalidUserException;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import de.fourzerofournotfound.rateyourstuff.rays.services.UserSecurityService;
import de.fourzerofournotfound.rateyourstuff.rays.services.UserService;

import javax.management.relation.RoleNotFoundException;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final UserSecurityService userSecurityService;

    @Autowired
    public UserController(UserRepository repository, UserService userService, UserSecurityService userSecurityService) {
        this.userRepository = repository;
        this.userService = userService;
        this.userSecurityService = userSecurityService;
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/all")
    ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(this.userRepository.findAll());
    }

    @GetMapping("/id={id}")
    ResponseEntity<User> getById(@PathVariable Long id) throws UserNotFoundException {
        return ResponseEntity.ok((this.userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("No User found for given id"))));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/check/is={userName}")
    ResponseEntity<Boolean> getUsername(@PathVariable String userName) throws UserAlreadyExistsException {
        Optional<User> user = userRepository.findByUserNameIgnoreCase(userName);
        if(user.isPresent()){
            throw new UserAlreadyExistsException("User tried to sign up with already taken userName: @" + userName);
        } else {
            return ResponseEntity.ok(true);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    ResponseEntity<User> add(@RequestBody User user) {
        userService.setRoleId(user);
        userSecurityService.hashPasswordOfSignUp(user);
        return ResponseEntity.ok(this.userRepository.save(user));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<User> update(@RequestBody User user) throws InvalidUserException {
        userService.addReferencesToUser(user);
        return ResponseEntity.ok(this.userRepository.save(user));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {this.userRepository.deleteById(id);}
}
