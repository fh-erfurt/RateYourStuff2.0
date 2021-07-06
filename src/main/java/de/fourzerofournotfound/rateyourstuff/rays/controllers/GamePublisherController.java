package de.fourzerofournotfound.rateyourstuff.rays.controllers;


import de.fourzerofournotfound.rateyourstuff.rays.models.GamePublisher;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.GamePublisherNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.GamePublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gamePublishers-rest")
public class GamePublisherController {

    private final GamePublisherRepository gamePublisherRepository;

    @Autowired
    public GamePublisherController(GamePublisherRepository gamePublisherRepository) {
        this.gamePublisherRepository = gamePublisherRepository;
    }

    @GetMapping("/all")
    ResponseEntity<List<GamePublisher>> getAll() {
        return ResponseEntity.ok(this.gamePublisherRepository.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<GamePublisher> getById(@PathVariable Long id) throws GamePublisherNotFoundException {
        return ResponseEntity.ok(this.gamePublisherRepository.findById(id).orElseThrow(() -> new GamePublisherNotFoundException("No Publisher found for id " + id))); }

    @GetMapping()
    ResponseEntity<GamePublisher> findByTitle(@RequestParam(value = "title") String title) throws GamePublisherNotFoundException {
        return ResponseEntity.ok(this.gamePublisherRepository.findByGamePublisherTitle(title).orElseThrow(() -> new GamePublisherNotFoundException("No Publisher with title " + title))); }

    @PostMapping(path="/add", consumes= "application/json", produces="application/json")
    ResponseEntity<GamePublisher> add(@RequestBody GamePublisher gamePublisher) {
        return ResponseEntity.ok(this.gamePublisherRepository.save(gamePublisher));
    }

    @PutMapping(consumes="application/json", produces="application/json")
    ResponseEntity<GamePublisher> update(@RequestBody GamePublisher gamePublisher) {
        return ResponseEntity.ok(this.gamePublisherRepository.save(gamePublisher));
    }

    @DeleteMapping("/{id}")
    void deletePublisher (@PathVariable Long id) {
        this.gamePublisherRepository.deleteById(id);
    }
}