package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.models.Series;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("series-rest")
public class SeriesController {

    @Autowired
    SeriesRepository repository;

    @GetMapping("/")
    List<Series> getAllSeries() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Series> getSeriesById(@PathVariable Long id) {
        return repository.findById(id);
    }


    @PostMapping("/add")
    Series addNewSeries(@RequestBody Series newSeries) {
        return repository.save(newSeries);
    }

}
