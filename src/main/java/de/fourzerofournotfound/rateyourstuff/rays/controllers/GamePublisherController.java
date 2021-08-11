package de.fourzerofournotfound.rateyourstuff.rays.controllers;


import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.GamePublisherDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.GamePublisher;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.GamePublisherNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.GamePublisherRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.GamePublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/{id}")
    ResponseEntity<GamePublisher> getById(@PathVariable Long id) throws GamePublisherNotFoundException {
        return ResponseEntity.ok(this.gamePublisherRepository.findById(id).orElseThrow(() -> new GamePublisherNotFoundException("No Publisher found for id " + id))); }
}