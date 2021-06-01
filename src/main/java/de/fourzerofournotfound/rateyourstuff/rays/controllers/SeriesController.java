package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.models.Series;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.SeriesNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/series-rest")
public class SeriesController {

    @Autowired
    SeriesRepository repository;

    @GetMapping("/all")
    ResponseEntity<List<Series>> getAll() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Series> getById (@PathVariable Long id) throws SeriesNotFoundException {
        return ResponseEntity.ok(this.repository.findById(id).orElseThrow(() -> new SeriesNotFoundException("No Series found for id " + id)));
    }

    @GetMapping()
    ResponseEntity<Series> findByTitle(@RequestParam(value="title") String title) throws SeriesNotFoundException {
        return ResponseEntity.ok(this.repository.findByMediumName(title).orElseThrow(() -> new SeriesNotFoundException("No Series with title " +title )));
    }

    @PostMapping(path="/add", consumes= "application/json", produces="application/json")
    ResponseEntity<Series> add(@RequestBody Series series) {
        return ResponseEntity.ok(this.repository.save(series));
    }

    @PutMapping(consumes="application/json", produces="application/json")
    ResponseEntity<Series> update(@RequestBody Series series) {
        return ResponseEntity.ok(this.repository.save(series));
    }

    @DeleteMapping("/{id}")
    void deleteSeries (@PathVariable Long id) {
        this.repository.deleteById(id);
    }

}
