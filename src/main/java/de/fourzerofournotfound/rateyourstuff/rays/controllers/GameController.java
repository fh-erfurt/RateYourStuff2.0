package de.fourzerofournotfound.rateyourstuff.rays.controllers;


import de.fourzerofournotfound.rateyourstuff.rays.models.Game;
import de.fourzerofournotfound.rateyourstuff.rays.models.Series;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.GameNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.GameRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games-rest")
public class GameController {

    @Autowired
    GameRepository repository;

    @Autowired
    FileUploadService fus;


    @GetMapping("/all")
    ResponseEntity<List<Game>> getAll() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Game> getById(@PathVariable Long id) throws GameNotFoundException {
        return ResponseEntity.ok(this.repository.findById(id).orElseThrow(() -> new GameNotFoundException("No Game found for id " + id))); }

    @GetMapping()
    ResponseEntity<Game> findByTitle(@RequestParam(value = "title") String title) throws GameNotFoundException {
        return ResponseEntity.ok(this.repository.findByMediumName(title).orElseThrow(() -> new GameNotFoundException("No Game with title " + title))); }

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

    @PutMapping("/images/{id}")
    ResponseEntity<Game> addImage(@RequestParam("image") MultipartFile multipartFile, @PathVariable Long id) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Optional<Game> game = this.repository.findById(id);
        //check if the given movie exists
        if(game.isPresent()) {
            game.get().setPicturePath(fileName);
            //define the target path
            String uploadDir = Game.IMAGE_PATH_PREFIX + id.toString();
            //upload the file
            fus.saveFile(uploadDir, fileName, multipartFile);
            return ResponseEntity.ok(this.repository.save(game.get()));
        }
        return ResponseEntity.badRequest().build();
    }
}