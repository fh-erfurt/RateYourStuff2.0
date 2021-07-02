package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.models.Season;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.SeasonNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/seasons")
public class SeasonController {

    @Autowired
    SeasonRepository repository;

    @GetMapping("/all")
    ResponseEntity<List<Season>> getAll() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Season> getById (@PathVariable Long id) throws SeasonNotFoundException {
        return ResponseEntity.ok(this.repository.findById(id).orElseThrow(() -> new SeasonNotFoundException("No Season found for id " + id)));
    }

    @GetMapping("/series/{id}")
    ResponseEntity<List<Season>> getByMediumId (@PathVariable Long id) throws SeasonNotFoundException {
        return ResponseEntity.ok(this.repository.findAllByMediumId(id));
    }

    @PostMapping(path="/add", consumes= "application/json", produces="application/json")
    ResponseEntity<Season> add(@RequestBody Season season) {
        return ResponseEntity.ok(this.repository.save(season));
    }

    @PutMapping(consumes="application/json", produces="application/json")
    ResponseEntity<Season> update(@RequestBody Season season) {
        return ResponseEntity.ok(this.repository.save(season));
    }

    @DeleteMapping("/{id}")
    void deleteSeason (@PathVariable Long id) {
        this.repository.deleteById(id);
    }

}
