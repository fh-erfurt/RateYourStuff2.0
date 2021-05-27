package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.models.Episode;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("episodes-rest")
public class EpisodeController {

    @Autowired
    EpisodeRepository repository;

    @GetMapping("/")
    List<Episode> getAllEpisodes() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Episode> getOneEpisode (@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping("/add")
    Episode addNewEpisode(@RequestBody Episode newEpisode) {
        return repository.save(newEpisode);
    }

}
