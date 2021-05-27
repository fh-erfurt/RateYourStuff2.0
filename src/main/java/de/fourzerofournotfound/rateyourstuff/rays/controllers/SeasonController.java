package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.models.Season;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("seasons-rest")
public class SeasonController {

    @Autowired
    SeasonRepository repository;

    @GetMapping("/")
    List<Season> getAllSeasons() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Season> getOneSeason(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping("/new")
    Season addNewSeason (@RequestBody Season newSeason) {
        return repository.save(newSeason);
    }

}
