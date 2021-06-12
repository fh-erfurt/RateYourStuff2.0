package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.models.Login;
import de.fourzerofournotfound.rateyourstuff.rays.models.User;
import de.fourzerofournotfound.rateyourstuff.rays.services.UserSecurityService;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.UserNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.LoginRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserSecurityService uss;

    @GetMapping("/all")
    ResponseEntity<List<User>> getAllUsers() {return ResponseEntity.ok(this.userRepository.findAll());}

    @GetMapping("/{id}")
    ResponseEntity<User> getUserById(@PathVariable Long id) throws UserNotFoundException
    {
        return ResponseEntity.ok(this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("No user found for id" + id)));
    }

    @PostMapping(path="/add", consumes = "application/json", produces = "application/json")
    ResponseEntity<User> addUser(@RequestBody User user)
    {
        uss.hashPasswordOfSignUp(user);
        return ResponseEntity.ok(this.userRepository.save(user));
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<User> updateUser(@RequestBody User user)
    {
        return ResponseEntity.ok(this.userRepository.save(user));
    }

    @PutMapping(path ="/changePassword", consumes = "application/json", produces = "application/json")
    ResponseEntity<User> changePassword(@RequestBody User user, String currentPassword, String newPassword)
    {
        uss.changePassword(currentPassword, newPassword, user);
        return ResponseEntity.ok(this.userRepository.save(user));
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {this.userRepository.deleteById(id);}




    //TODO: Methods

    //TODO: Update User

    //TODO: Update Password
    //TODO: Reset Password


}
