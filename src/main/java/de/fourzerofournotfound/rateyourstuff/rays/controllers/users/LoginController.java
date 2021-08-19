package de.fourzerofournotfound.rateyourstuff.rays.controllers.users;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.users.LoginDto;
import de.fourzerofournotfound.rateyourstuff.rays.errors.users.EmailAlreadyExistsException;
import de.fourzerofournotfound.rateyourstuff.rays.errors.users.InvalidLoginException;
import de.fourzerofournotfound.rateyourstuff.rays.errors.users.LoginNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.users.Login;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.users.LoginRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.users.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;

/**
 * <p>This Controller provides basic REST Interfaces to interact with Login entities from the database</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */

@RestController
@RequestMapping("/login")
public class LoginController {
    private final LoginRepository repository;
    private final LoginService loginService;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    public LoginController(LoginRepository repository,
                           LoginService loginService) {
        this.repository = repository;
        this.loginService = loginService;
    }

    /**
     * Method returns Login which belongs to given email address
     *
     * @param email for searched login profile
     * @return Login object of if searched profile is valid
     * @throws LoginNotFoundException if no valid profile was found
     */
    @GetMapping("/getMail")
    ResponseEntity<Login> getUserName(@RequestParam String email) throws LoginNotFoundException {
        return ResponseEntity.ok((this.repository.findLoginByEmailNotIgnoreCase(email).orElseThrow(() ->
                new LoginNotFoundException("No Login found for given email"))));
    }

    /**
     * Method updates the login entity in db with given login object from web-api
     *
     * @param login which contains the data to update current db entry
     * @return Dto of login-profile after update
     * @throws InvalidLoginException       if given login has no valid reference in db
     * @throws EmailAlreadyExistsException if email address of given login object already exists
     */
    @PreAuthorize("hasAuthority('User')")
    @PutMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<LoginDto> update(@RequestBody Login login) throws InvalidLoginException, EmailAlreadyExistsException {
        Optional<Login> potentialLogin = repository.findLoginById(login.getId());
        if (potentialLogin.isPresent()) {
            loginService.manageUpdatePersistence(login, potentialLogin);
            Login savedLogin = repository.save(potentialLogin.get());
            logger.info("Updated " + Login.class.getSimpleName() + " with id " + savedLogin.getId());
            return ResponseEntity.ok(this.loginService.convertToDto(savedLogin));
        }

        throw new InvalidLoginException(Login.class.getSimpleName() + " not found!");
    }

    /**
     * Method checks if given mail is valid in database and returns the result
     *
     * @param email given from web-api
     * @return true if the given email address isn´t already used of any other user
     * @throws EmailAlreadyExistsException if the given email address is already used
     */

    @PostMapping("/check")
    ResponseEntity<Boolean> isValidMail(@RequestBody String email) throws EmailAlreadyExistsException {
        Optional<Login> login = repository.findLoginByEmailIgnoreCase(email);
        if (login.isPresent()) {
            throw new EmailAlreadyExistsException("User tried to sign up with already taken email: @" + email);
        } else {
            return ResponseEntity.ok(true);
        }
    }
}
