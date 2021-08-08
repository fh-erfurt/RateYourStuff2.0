package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.models.Platform;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.PlatformNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/platforms")
public class PlatformController {

    private final PlatformRepository repository;

    @Autowired
    public PlatformController(PlatformRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    ResponseEntity<List<Platform>> getAll() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Platform> getById(@PathVariable Long id) throws PlatformNotFoundException {
        return ResponseEntity.ok(this.repository.findById(id).orElseThrow(() -> new PlatformNotFoundException("No Platform found for id " + id))); }

    @GetMapping()
    ResponseEntity<Platform> findByTitle(@RequestParam(value = "title") String title) throws PlatformNotFoundException {
        return ResponseEntity.ok(this.repository.findByPlatformTitle(title).orElseThrow(() -> new PlatformNotFoundException("No Platform with title " + title))); }

    @PostMapping(path="/add", consumes= "application/json", produces="application/json")
    ResponseEntity<Platform> add(@RequestBody Platform platform) {
        return ResponseEntity.ok(this.repository.save(platform));
    }

    @PutMapping(consumes="application/json", produces="application/json")
    ResponseEntity<Platform> update(@RequestBody Platform platform) {
        return ResponseEntity.ok(this.repository.save(platform));
    }

    @DeleteMapping("/{id}")
    void deletePlatform (@PathVariable Long id) {
        this.repository.deleteById(id);
    }
}