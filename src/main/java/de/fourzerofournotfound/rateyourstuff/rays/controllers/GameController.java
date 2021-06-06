package de.fourzerofournotfound.rateyourstuff.rays.controllers;


import de.fourzerofournotfound.rateyourstuff.rays.models.Game;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.GameNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/episodes-rest")
public class GameController {

    @Autowired
    GameRepository repository;

    @GetMapping("/all")
    ResponseEntity<List<Game>> getAll() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Game> getById(@PathVariable Long id) throws GameNotFoundException {
        return ResponseEntity.ok(this.repository.findById(id).orElseThrow(() -> new GameNotFoundException("No Game found for id " + id))); }

    @GetMapping()
    ResponseEntity<Game> findByTitle(@RequestParam(value = "title") String title) throws GameNotFoundException {
        return ResponseEntity.ok(this.repository.findByMediumName(title).orElseThrow(() -> new GameNotFoundException("No Episode with title " + title))); }

    @PostMapping(path="/add", consumes= "application/json", produces="application/json")
    ResponseEntity<Game> add(@RequestBody Game game) {
        return ResponseEntity.ok(this.repository.save(game));
    }

    @PutMapping(consumes="application/json", produces="application/json")
    ResponseEntity<Game> update(@RequestBody Game game) {
        return ResponseEntity.ok(this.repository.save(game));
    }

    @DeleteMapping("/{id}")
    void deleteGame (@PathVariable Long id) {
        this.repository.deleteById(id);
    }
}