package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.models.User;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.UserNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository repository;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/all")
    ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{getUserName}")
    ResponseEntity<User> getUserName (@PathVariable String userName) throws UserNotFoundException{
        return ResponseEntity.ok((this.repository.findByUserName(userName).orElseThrow(()-> new UserNotFoundException("No User found for given User Name"))));
    }

    @GetMapping("/{id}")
    ResponseEntity<User> getById(@PathVariable Long id) throws UserNotFoundException {
        return ResponseEntity.ok((this.repository.findById(id).orElseThrow(()-> new UserNotFoundException("No User found for given id"))));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    ResponseEntity<User> add(@RequestBody User user) {
        return ResponseEntity.ok(this.repository.save(user));
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<User> update(@RequestBody User user) {
        return ResponseEntity.ok(this.repository.save(user));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {this.repository.deleteById(id);}
}
