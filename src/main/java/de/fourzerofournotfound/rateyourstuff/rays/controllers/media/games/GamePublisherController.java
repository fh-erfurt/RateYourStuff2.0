package de.fourzerofournotfound.rateyourstuff.rays.controllers.media.games;


import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.games.GamePublisherDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.games.GamePublisherNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.games.GamePublisher;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.games.GamePublisherRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.games.GamePublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * GamePublisher Controller
 * <p>This Controller provides basic REST Interfaces to interact with GamePublisher entities from the database</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@RestController
@RequestMapping("/rest/gamePublisher")
public class GamePublisherController {

    private final GamePublisherRepository gamePublisherRepository;
    private final PageableService pageableService;
    private final GamePublisherService gamePublisherService;

    @Autowired
    public GamePublisherController(GamePublisherRepository gamePublisherRepository, PageableService pageableService, GamePublisherService gamePublisherService) {
        this.gamePublisherRepository = gamePublisherRepository;
        this.pageableService = pageableService;
        this.gamePublisherService = gamePublisherService;
    }

    /**
     * This Method returns all GamePublishers from the database
     *
     * @param page    the current page (optional)
     * @param size    the number of items per page
     * @param orderBy the attributed that should be ordered
     * @param order   the order (asc, desc)
     * @return a list of GamePublisherDTOs
     */
    @GetMapping("/all")
    ResponseEntity<List<GamePublisherDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "") String orderBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Pageable pageable = pageableService.createPageable(orderBy, order, page, size);
        List<GamePublisher> publishers = this.gamePublisherRepository.findAll(pageable).getContent();

        return ResponseEntity.ok(publishers.stream()
                .map(gamePublisherService::convertToDto)
                .collect(Collectors.toList()));
    }

    /**
     * This method is used to return a single GamePublisher by its id
     *
     * @param id the id of the gamePublisher
     * @return the found GamePublisherDTO
     * @throws GamePublisherNotFoundException if there is no GamePublisher with the given id
     */
    @GetMapping("/{id}")
    ResponseEntity<GamePublisherDto> getById(@PathVariable Long id) throws GamePublisherNotFoundException {
        Optional<GamePublisher> publisher = this.gamePublisherRepository.findById(id);
        if (publisher.isPresent()) {
            return ResponseEntity.ok(gamePublisherService.convertToDto(publisher.get()));
        }
        throw new GamePublisherNotFoundException("No Publisher found for id " + id);
    }
}