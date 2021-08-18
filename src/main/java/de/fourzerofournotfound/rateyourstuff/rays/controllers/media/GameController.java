package de.fourzerofournotfound.rateyourstuff.rays.controllers.media;


import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.GameDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.GameNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Game;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.GameRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.FileUploadService;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.DuplicateMediumException;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Game Controller
 * <p>This Controller provides basic REST Interfaces to interact with Game entities from the database</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@RestController
@RequestMapping("/rest/games")
public class GameController {

    final GameRepository gameRepository;
    final FileUploadService fileUploadService;
    final PageableService pageableService;
    final GameService gameService;
    private final PlatformService platformService;
    private final LanguageService languageService;
    private final GenreService genreService;
    private final GamePublisherService gamePublisherService;

    @Autowired
    public GameController(GameRepository gameRepository,
                          FileUploadService fileUploadService,
                          PageableService pageableService,
                          GameService gameService,
                          PlatformService platformService,
                          LanguageService languageService,
                          GenreService genreService, GamePublisherService gamePublisherService) {
        this.gameRepository = gameRepository;
        this.fileUploadService = fileUploadService;
        this.pageableService = pageableService;
        this.gameService = gameService;
        this.platformService = platformService;
        this.languageService = languageService;
        this.genreService = genreService;
        this.gamePublisherService = gamePublisherService;
    }

    /**
     * This Method returns all games from the database
     *
     * @param page    the current page (optional)
     * @param size    the number of items per page
     * @param orderBy the attributed that should be ordered
     * @param order   the order (asc, desc)
     * @return a list of GameDTOs
     */
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

    /**
     * This method is used to get a single Game DTO by its id
     *
     * @param id the id of the game
     * @return the found GameDTO
     * @throws GameNotFoundException if there is no game with the given id
     */
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


    /**
     * This method is used to add a game to the database.
     *
     * @param game the game that should be added
     * @return the GameDTO of the new game
     * @throws DuplicateMediumException if there is already the same game in the database
     */
    @PreAuthorize("hasAuthority('User')")
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    ResponseEntity<GameDto> add(@RequestBody Game game) throws DuplicateMediumException {
        if (this.gameService.isValidGame(game)) {
            this.gameRepository.save(game);
            game.setGenres(this.genreService.getGenresSet(game.getGenreStrings()));
            game.setLanguages(this.languageService.getLanguageSet(game.getLanguageStrings()));
            game.setGamePublisher(this.gamePublisherService.getPublisher(game.getPublisherTitle()));
            game.setPlatforms(this.platformService.getPlatformSet(game.getPlatformStrings()));
            Game savedGame = this.gameRepository.save(game);
            return ResponseEntity.ok(gameService.convertToDto(savedGame));
        } else {
            throw new DuplicateMediumException("The Game " + game.getMediumName() + " already exists.");
        }
    }

    /**
     * This method is used to update a given game
     *
     * @param game the game that should be updated
     * @return the GameDTO of the updated game
     * @throws DuplicateMediumException if the change would conflict with another game
     */
    @PreAuthorize("hasAuthority('User')")
    @PutMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<GameDto> update(@RequestBody Game game) throws DuplicateMediumException {
        if (this.gameService.isValidGame(game)) {
            game.setGamePublisher(this.gamePublisherService.getPublisher(game.getPublisherTitle()));
            this.gameRepository.save(game);
            game.setGenres(this.genreService.getGenresSet(game.getGenreStrings()));
            game.setLanguages(this.languageService.getLanguageSet(game.getLanguageStrings()));
            game.setPlatforms(this.platformService.getPlatformSet(game.getPlatformStrings()));
            Game savedGame = this.gameRepository.save(game);
            return ResponseEntity.ok(gameService.convertToDto(savedGame));
        } else {
            throw new DuplicateMediumException("The Game " + game.getMediumName() + " already exists.");
        }
    }


    /**
     * This method is used to attach a poster to a game
     *
     * @param multipartFile the image file that should be attached
     * @param id            the id of the game that needs to be updated
     * @return the updated game
     * @throws IOException           if the upload fails
     * @throws GameNotFoundException if there is no game with the given id
     */
    @PreAuthorize("hasAuthority('User')")
    @PostMapping("/images/{id}")
    ResponseEntity<GameDto> addImage(@RequestParam("image") MultipartFile multipartFile, @PathVariable Long id) throws IOException, GameNotFoundException {
        String fileName = StringUtils.cleanPath("poster." + fileUploadService.getFileExtension(multipartFile));

        Optional<Game> game = this.gameRepository.findById(id);
        //check if the given movie exists
        if (game.isPresent()) {
            game.get().setPicturePath(game.get().getId() + "/" + fileName);
            //define the target path
            String uploadDir = Game.IMAGE_PATH_PREFIX + id;
            //upload the file
            fileUploadService.saveFile(uploadDir, fileName, multipartFile);
            Game savedGame = this.gameRepository.save(game.get());
            return ResponseEntity.ok(gameService.convertToDto(savedGame));
        }
        throw new GameNotFoundException("There is no game with the id " + id);
    }
}