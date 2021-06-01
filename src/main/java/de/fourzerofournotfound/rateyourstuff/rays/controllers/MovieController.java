package de.fourzerofournotfound.rateyourstuff.rays.controllers;


import de.fourzerofournotfound.rateyourstuff.rays.models.Movie;

import de.fourzerofournotfound.rateyourstuff.rays.models.errors.MovieNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies-rest")
public class MovieController {
    @Autowired
    private MovieRepository repository;

    @GetMapping("/all")
    ResponseEntity<List<Movie>> getAll() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Movie> getById (@PathVariable Long id) throws MovieNotFoundException {
        return ResponseEntity.ok(this.repository.findById(id).orElseThrow(() -> new MovieNotFoundException("No Movie found for id " + id)));
    }

    @GetMapping()
    ResponseEntity<Movie> findByTitle(@RequestParam(value="title") String title) throws MovieNotFoundException {
        return ResponseEntity.ok(this.repository.findByMediumName(title).orElseThrow(() -> new MovieNotFoundException("No Movie with title " +title )));
    }

    @PostMapping(path="/add", consumes= "application/json", produces="application/json")
    ResponseEntity<Movie> add(@RequestBody Movie movie) {
        return ResponseEntity.ok(this.repository.save(movie));
    }

    @PutMapping(consumes="application/json", produces="application/json")
    ResponseEntity<Movie> update(@RequestBody Movie movie) {
        return ResponseEntity.ok(this.repository.save(movie));
    }

    @DeleteMapping("/{id}")
    void deleteMovie (@PathVariable Long id) {
        this.repository.deleteById(id);
    }

}