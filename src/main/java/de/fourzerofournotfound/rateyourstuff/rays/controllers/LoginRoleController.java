package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.models.Login;
import de.fourzerofournotfound.rateyourstuff.rays.models.LoginRole;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.LoginRoleRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.LoginRoleService;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.InvalidLoginRoleException;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.InvalidRoleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/role")

public class LoginRoleController {
    private final LoginRoleRepository loginRoleRepository;
    private final LoginRoleService loginRoleService;

    @Autowired
    LoginRoleController(LoginRoleRepository repository, LoginRoleService service) {
        this.loginRoleRepository = repository;
        this.loginRoleService = service;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    ResponseEntity<LoginRole> add(@RequestBody LoginRole loginRole) throws InvalidRoleException {
        loginRoleService.setRoleId(loginRole);
        return ResponseEntity.ok(this.loginRoleRepository.save(loginRole));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<LoginRole> update(@RequestBody LoginRole loginRole) throws InvalidLoginRoleException {
        return ResponseEntity.ok(this.loginRoleRepository.save(loginRole));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/id")
    void deleteUser(@PathVariable Long LoginId) throws InvalidLoginRoleException {this.loginRoleRepository.delete(
            loginRoleService.loginRoleValidator(
            this.loginRoleRepository.findLoginRoleByLoginId(LoginId))
            );
    }

}
