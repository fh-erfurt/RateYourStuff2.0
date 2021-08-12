package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.models.Login;
import de.fourzerofournotfound.rateyourstuff.rays.models.User;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.LoginNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.LoginRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.UserSecurityService;
import de.fourzerofournotfound.rateyourstuff.rays.services.UserService;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.EmailAlreadyExistsException;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.InvalidLoginException;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;
    private final UserSecurityService userSecurityService;
    private final LoginRepository repository;

    @Autowired
    public LoginController(UserService userService,
                           UserSecurityService userSecurityService,
                           LoginRepository repository) {
        this.userService = userService;
        this.userSecurityService = userSecurityService;
        this.repository = repository;
    }

    @GetMapping("/getMail")
    ResponseEntity<Login> getUserName (@RequestParam String email) throws LoginNotFoundException {
        return ResponseEntity.ok((this.repository.findLoginByEmailNotIgnoreCase(email).orElseThrow(()-> new LoginNotFoundException("No Login found for given email"))));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<Login> update(@RequestBody Login login) throws InvalidLoginException {
        userSecurityService.hashPasswordOfLogin(login);
        userService.addReferencesToLogin(login);
        return ResponseEntity.ok(this.repository.save(login));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/check")
    ResponseEntity<Boolean> getUsername(@RequestBody String email) throws EmailAlreadyExistsException {
        Optional<Login> login = repository.findLoginByEmailIgnoreCase(email);
        if(login.isPresent()){
            throw new EmailAlreadyExistsException("User tried to sign up with already taken enauk: @" + email);
        } else {
            return ResponseEntity.ok(true);
        }
    }
}
