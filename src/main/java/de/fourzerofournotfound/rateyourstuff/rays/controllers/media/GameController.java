package de.fourzerofournotfound.rateyourstuff.rays.controllers.media;


import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.GameDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Game;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.GameNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.GameRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.FileUploadService;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.MediaService;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.DuplicateMediumException;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/games")
public class GameController {

    final GameRepository gameRepository;
    final FileUploadService fileUploadService;
    final PageableService pageableService;
    final GameService gameService;
    private final MediaService mediaService;

    @Autowired
    public GameController(GameRepository gameRepository,
                          FileUploadService fileUploadService,
                          PageableService pageableService,
                          GameService gameService, MediaService mediaService) {
        this.gameRepository = gameRepository;
        this.fileUploadService = fileUploadService;
        this.pageableService = pageableService;
        this.gameService = gameService;
        this.mediaService = mediaService;
    }

    @GetMapping("/all")
    ResponseEntity<List<GameDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "") String orderBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Pageable pageable = pageableService.createPageable(orderBy, order, page, size);
        List<Game> games = this.gameRepository.findAll(pageable).getContent();
        return ResponseEntity.ok(
                games.stream().map(gameService::convertToDto).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    ResponseEntity<GameDto> getById(@PathVariable Long id) throws GameNotFoundException {
        Optional<Game> game = this.gameRepository.findById(id);
        if (game.isPresent()) {
            GameDto gameDto = gameService.convertToDto(game.get());
            return ResponseEntity.ok(gameDto);
        } else {
            throw new GameNotFoundException("No Game found for id " + id);
        }
    }

    @GetMapping()
    ResponseEntity<GameDto> findByTitle(@RequestParam(value = "title") String title) throws GameNotFoundException {
        Optional<Game> game = this.gameRepository.findByMediumName(title);
        if (game.isPresent()) {
            GameDto gameDto = gameService.convertToDto(game.get());
            return ResponseEntity.ok(gameDto);
        } else {
            throw new GameNotFoundException("No Game with title " + title);
        }
    }

    @PostMapping(path="/add", consumes= "application/json", produces="application/json")
    ResponseEntity<Game> add(@RequestBody Game game) throws DuplicateMediumException {
        if(this.gameService.isValidGame(game)) {
            this.gameRepository.save(game);
            game.setGenres(this.mediaService.getGenresSet(game.getGenreStrings(), game));
            game.setLanguages(this.mediaService.getLanguageSet(game.getLanguageStrings(), game));
            game.setGamePublisher(this.gameService.getPublisher(game.getPublisherTitle(), game));
            game.setPlatforms(this.gameService.getPlatformSet(game.getPlatformStrings(), game));
            return ResponseEntity.ok(this.gameRepository.save(game));
        } else {
            throw new DuplicateMediumException("The Game " + game.getMediumName() + " already exists.");
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping(consumes="application/json", produces="application/json")
    ResponseEntity<Game> update(@RequestBody Game game) throws DuplicateMediumException {
        if(this.gameService.isValidGame(game)) {
            game.setGamePublisher(this.gameService.getPublisher(game.getPublisherTitle(), game));
            this.gameRepository.save(game);
            game.setGenres(this.mediaService.getGenresSet(game.getGenreStrings(), game));
            game.setLanguages(this.mediaService.getLanguageSet(game.getLanguageStrings(), game));
            game.setPlatforms(this.gameService.getPlatformSet(game.getPlatformStrings(), game));
            return ResponseEntity.ok(this.gameRepository.save(game));
        } else {
            throw new DuplicateMediumException("The Game " + game.getMediumName() + " already exists.");
        }
    }

    @DeleteMapping("/{id}")
    void deleteGame (@PathVariable Long id) {
        this.gameRepository.deleteById(id);
    }

    @PostMapping("/images/{id}")
    ResponseEntity<Game> addImage(@RequestParam("image") MultipartFile multipartFile, @PathVariable Long id) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        Optional<Game> game = this.gameRepository.findById(id);
        //check if the given movie exists
        if(game.isPresent()) {
            game.get().setPicturePath(game.get().getId() + "/" + fileName);
            //define the target path
            String uploadDir = Game.IMAGE_PATH_PREFIX + id;
            //upload the file
            fileUploadService.saveFile(uploadDir, fileName, multipartFile);
            return ResponseEntity.ok(this.gameRepository.save(game.get()));
        }
        return ResponseEntity.badRequest().build();
    }
}