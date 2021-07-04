package de.fourzerofournotfound.rateyourstuff.rays.controllers;


import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.GameDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Game;
import de.fourzerofournotfound.rateyourstuff.rays.models.Series;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.GameNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.GameRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.FileUploadService;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/games")
public class GameController {

    @Autowired
    GameRepository repository;

    @Autowired
    FileUploadService fus;

    @Autowired
    PageableService pageableService;

    @Autowired
    GameService gameService;

    @GetMapping("/all")
    ResponseEntity<List<GameDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "") String orderBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Pageable pageable = pageableService.createPageable(orderBy, order, page, size);
        List<Game> games = this.repository.findAll(pageable).getContent();
        return ResponseEntity.ok(
                games.stream().map(gameService::convertToDto).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    ResponseEntity<GameDto> getById(@PathVariable Long id) throws GameNotFoundException {
        Optional<Game> game = this.repository.findById(id);
        if (game.isPresent()) {
            GameDto gameDto = gameService.convertToDto(game.get());
            return ResponseEntity.ok(gameDto);
        } else {
            throw new GameNotFoundException("No Game found for id " + id);
        }
    }

    @GetMapping()
    ResponseEntity<GameDto> findByTitle(@RequestParam(value = "title") String title) throws GameNotFoundException {
        Optional<Game> game = this.repository.findByMediumName(title);
        if (game.isPresent()) {
            GameDto gameDto = gameService.convertToDto(game.get());
            return ResponseEntity.ok(gameDto);
        } else {
            throw new GameNotFoundException("No Game with title " + title);
        }
    }

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
            game.get().setPicturePath(game.get().getId() + "/" + fileName);
            //define the target path
            String uploadDir = Game.IMAGE_PATH_PREFIX + id.toString();
            //upload the file
            fus.saveFile(uploadDir, fileName, multipartFile);
            return ResponseEntity.ok(this.repository.save(game.get()));
        }
        return ResponseEntity.badRequest().build();
    }
}