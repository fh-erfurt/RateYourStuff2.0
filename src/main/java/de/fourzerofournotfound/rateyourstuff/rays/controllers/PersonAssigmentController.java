package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.models.PersonAssignment;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.PersonAssigmentNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.PersonAssigmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonAssigmentController {

    private final PersonAssigmentRepository personAssigmentRepository;

    @Autowired
    public PersonAssigmentController(PersonAssigmentRepository personAssigmentRepository) {
        this.personAssigmentRepository = personAssigmentRepository;
    }

    @GetMapping("/all")
    ResponseEntity<List<PersonAssignment>> getAll() {
        return ResponseEntity.ok(this.personAssigmentRepository.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<PersonAssignment> getById(@PathVariable Long id) throws PersonAssigmentNotFoundException {
        return ResponseEntity.ok(this.personAssigmentRepository.findById(id).orElseThrow(() -> new PersonAssigmentNotFoundException("No Platform found for id " + id))); }


    @PostMapping(path="/add", consumes= "application/json", produces="application/json")
    ResponseEntity<PersonAssignment> add(@RequestBody PersonAssignment personAssignment) {
        return ResponseEntity.ok(this.personAssigmentRepository.save(personAssignment));
    }

    @PutMapping(consumes="application/json", produces="application/json")
    ResponseEntity<PersonAssignment> update(@RequestBody PersonAssignment personAssignment) {
        return ResponseEntity.ok(this.personAssigmentRepository.save(personAssignment));
    }

    @DeleteMapping("/{id}")
    void deletePersonAssignment (@PathVariable Long id) {
        this.personAssigmentRepository.deleteById(id);
    }
}
