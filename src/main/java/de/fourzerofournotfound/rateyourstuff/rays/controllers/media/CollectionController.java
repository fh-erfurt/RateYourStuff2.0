package de.fourzerofournotfound.rateyourstuff.rays.controllers.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.CollectionDto;
import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.MovieDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.UserNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.CollectionNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.MovieNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Collection;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Movie;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.CollectionRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/collections")
public class CollectionController {
    private final CollectionRepository collectionRepository;
    private final CollectionService collectionService;
    private final PageableService pageableService;

    @Autowired
    public CollectionController(CollectionRepository collectionRepository,
                                CollectionService collectionService, PageableService pageableService) {
        this.collectionRepository = collectionRepository;
        this.collectionService = collectionService;
        this.pageableService = pageableService;
    }

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    ResponseEntity<Collection> add(@RequestBody Collection collection) throws UserNotFoundException {
        collection = this.collectionService.addReferencesToCollection(collection);
        return ResponseEntity.ok(this.collectionRepository.save(collection));
    }

    @GetMapping(path = "/user/{id}")
    ResponseEntity<List<CollectionDto>> getAllByUser(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "") String orderBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Pageable pageable = pageableService.createPageable(orderBy, order, page, size);
        List<Collection> collections = this.collectionRepository.findAllByUserId(id, pageable).getContent();

        return ResponseEntity.ok(
                collections.stream()
                        .map(collectionService::convertToDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping(path = "/medium/{id}")
    ResponseEntity<List<CollectionDto>> getAllByMedium(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "") String orderBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Pageable pageable = pageableService.createPageable(orderBy, order, page, size);
        List<Collection> collections = this.collectionRepository.findAllByMediaId(id, pageable).getContent();

        return ResponseEntity.ok(
                collections.stream()
                        .map(collectionService::convertToDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping(path="/{id}")
    ResponseEntity<CollectionDto> getOne(@PathVariable Long id) throws CollectionNotFoundException {
        Optional<Collection> collection = this.collectionRepository.findById(id);
        if(collection.isPresent()) {
            CollectionDto collectionDto = collectionService.convertToDto(collection.get());
            return ResponseEntity.ok(collectionDto);
        } else {
            throw new CollectionNotFoundException("No Collection found for id " + id);
        }
    }

}
