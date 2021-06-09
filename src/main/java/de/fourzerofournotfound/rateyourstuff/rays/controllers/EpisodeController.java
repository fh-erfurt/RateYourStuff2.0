package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.models.Episode;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.EpisodeNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/episodes-rest")
public class EpisodeController {

    @Autowired
    EpisodeRepository repository;

    @GetMapping("/all")
    List<Episode> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<Episode> getById (@PathVariable Long id) throws EpisodeNotFoundException {
        return ResponseEntity.ok(this.repository.findById(id).orElseThrow(() -> new EpisodeNotFoundException("No Episode found for id " + id)));
    }

    @GetMapping()
    ResponseEntity<Episode> findByTitle(@RequestParam(value="title") String title) throws EpisodeNotFoundException {
        return ResponseEntity.ok(this.repository.findByMediumName(title).orElseThrow(() -> new EpisodeNotFoundException("No Episode with title " +title )));
    }

    @PostMapping(path="/add", consumes= "application/json", produces="application/json")
    ResponseEntity<Episode> add(@RequestBody Episode episode) {
        return ResponseEntity.ok(this.repository.save(episode));
    }

    @PutMapping(consumes="application/json", produces="application/json")
    ResponseEntity<Episode> update(@RequestBody Episode episode) {
        return ResponseEntity.ok(this.repository.save(episode));
    }

    @DeleteMapping("/{id}")
    void deleteEpisode (@PathVariable Long id) {
        this.repository.deleteById(id);
    }

}
