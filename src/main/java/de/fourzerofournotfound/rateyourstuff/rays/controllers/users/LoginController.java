package de.fourzerofournotfound.rateyourstuff.rays.controllers.users;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.users.LoginDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.users.Login;
import de.fourzerofournotfound.rateyourstuff.rays.errors.users.LoginNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.users.LoginRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.users.UserService;
import de.fourzerofournotfound.rateyourstuff.rays.services.users.LoginService;
import de.fourzerofournotfound.rateyourstuff.rays.services.users.UserSecurityService;
import de.fourzerofournotfound.rateyourstuff.rays.errors.users.EmailAlreadyExistsException;
import de.fourzerofournotfound.rateyourstuff.rays.errors.users.InvalidLoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;
    private final UserSecurityService userSecurityService;
    private final LoginRepository repository;
    private final LoginService loginService;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    public LoginController(UserService userService,
                           UserSecurityService userSecurityService,
                           LoginRepository repository,
                           LoginService loginService) {
        this.userService = userService;
        this.userSecurityService = userSecurityService;
        this.repository = repository;
        this.loginService = loginService;
    }

    @GetMapping("/getMail")
    ResponseEntity<Login> getUserName (@RequestParam String email) throws LoginNotFoundException {
        return ResponseEntity.ok((this.repository.findLoginByEmailNotIgnoreCase(email).orElseThrow(()-> new LoginNotFoundException("No Login found for given email"))));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<LoginDto> update(@RequestBody Login login) throws InvalidLoginException, EmailAlreadyExistsException {
        Optional<Login> potentialLogin = repository.findLoginById(login.getId());
        if(potentialLogin.isPresent()){
            loginService.manageUpdatePersistence(login, potentialLogin);
            Login savedLogin = repository.save(potentialLogin.get());
            logger.info("Updated " +Login.class.getSimpleName()+ " with id " + savedLogin.getId());
            return ResponseEntity.ok(this.loginService.convertToDto(savedLogin));
        }

        throw new InvalidLoginException(Login.class.getSimpleName() + " not found!");
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/check")
    ResponseEntity<Boolean> getEmail(@RequestBody String email) throws EmailAlreadyExistsException {
        Optional<Login> login = repository.findLoginByEmailIgnoreCase(email);
        if(login.isPresent()){
            throw new EmailAlreadyExistsException("User tried to sign up with already taken email: @" + email);
        } else {
            return ResponseEntity.ok(true);
        }
    }
}