package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.models.Network;
import de.fourzerofournotfound.rateyourstuff.rays.models.Season;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.NetworkNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.SeasonNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.NetworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/networks-rest")
public class NetworkController {

    @Autowired
    private NetworkRepository repository;

    @GetMapping("/all")
    ResponseEntity<List<Network>> getAll() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Network> getById (@PathVariable Long id) throws NetworkNotFoundException {
        return ResponseEntity.ok(this.repository.findById(id).orElseThrow(() -> new NetworkNotFoundException("No Network found for id " + id)));
    }

    @PostMapping(path="/add", consumes= "application/json", produces="application/json")
    ResponseEntity<Network> add(@RequestBody Network network) {
        return ResponseEntity.ok(this.repository.save(network));
    }

    @PutMapping(consumes="application/json", produces="application/json")
    ResponseEntity<Network> update(@RequestBody Network network) {
        return ResponseEntity.ok(this.repository.save(network));
    }

    @DeleteMapping("/{id}")
    void delete (@PathVariable Long id) {
        this.repository.deleteById(id);
    }
}
