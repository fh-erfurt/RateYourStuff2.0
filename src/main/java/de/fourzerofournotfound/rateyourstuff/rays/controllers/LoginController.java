package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.models.Login;
import de.fourzerofournotfound.rateyourstuff.rays.models.User;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.LoginNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.UserNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginRepository repository;

    @GetMapping("/getMail")
    ResponseEntity<Login> getUserName (@PathVariable String email) throws LoginNotFoundException {
        return ResponseEntity.ok((this.repository.findLoginByEmailNotIgnoreCase(email).orElseThrow(()-> new LoginNotFoundException("No Login found for given email"))));
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<Login> update(@RequestBody Login login) {return ResponseEntity.ok(this.repository.save(login));}
}
