package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.models.Movie;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("movies-rest")
public class MovieController {
    @Autowired
    private MovieRepository repository;

    @GetMapping("/")
    List<Movie> getAllMovies() {
        return repository.findAll();
    }

    @PostMapping("/add")
    Movie addNewMovie (@RequestBody Movie newMovie) {
        return repository.save(newMovie);
    }


    @GetMapping("/{id}")
    Optional<Movie> getMovieById(@PathVariable Long id) {
        return repository.findById(id);
    }



}