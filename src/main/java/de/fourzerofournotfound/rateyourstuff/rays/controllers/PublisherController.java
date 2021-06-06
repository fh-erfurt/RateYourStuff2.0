package de.fourzerofournotfound.rateyourstuff.rays.controllers;


import de.fourzerofournotfound.rateyourstuff.rays.models.Publisher;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.PublisherNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/episodes-rest")
public class PublisherController {

    @Autowired
    PublisherRepository repository;

    @GetMapping("/all")
    ResponseEntity<List<Publisher>> getAll() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Publisher> getById(@PathVariable Long id) throws PublisherNotFoundException {
        return ResponseEntity.ok(this.repository.findById(id).orElseThrow(() -> new PublisherNotFoundException("No Publisher found for id " + id))); }

    @GetMapping()
    ResponseEntity<Publisher> findByTitle(@RequestParam(value = "title") String title) throws PublisherNotFoundException {
        return ResponseEntity.ok(this.repository.findByMediumName(title).orElseThrow(() -> new PublisherNotFoundException("No Episode with title " + title))); }

    @PostMapping(path="/add", consumes= "application/json", produces="application/json")
    ResponseEntity<Publisher> add(@RequestBody Publisher publisher) {
        return ResponseEntity.ok(this.repository.save(publisher));
    }

    @PutMapping(consumes="application/json", produces="application/json")
    ResponseEntity<Publisher> update(@RequestBody Publisher publisher) {
        return ResponseEntity.ok(this.repository.save(publisher));
    }

    @DeleteMapping("/{id}")
    void deletePublisher (@PathVariable Long id) {
        this.repository.deleteById(id);
    }
}