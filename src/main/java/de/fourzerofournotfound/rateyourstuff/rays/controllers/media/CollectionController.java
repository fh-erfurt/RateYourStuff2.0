package de.fourzerofournotfound.rateyourstuff.rays.controllers.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.CollectionDto;
import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.ReducedCollectionDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.UserNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.CollectionNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Collection;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Medium;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.CollectionRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.MediaRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/collections")
public class CollectionController {
    private final CollectionRepository collectionRepository;
    private final CollectionService collectionService;
    private final PageableService pageableService;
    private final MediaRepository mediaRepository;

    @Autowired
    public CollectionController(CollectionRepository collectionRepository,
                                CollectionService collectionService, PageableService pageableService, MediaRepository mediaRepository) {
        this.collectionRepository = collectionRepository;
        this.collectionService = collectionService;
        this.pageableService = pageableService;
        this.mediaRepository = mediaRepository;
    }

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    ResponseEntity<Collection> add(@RequestBody Collection collection) throws UserNotFoundException {
        collection = this.collectionService.addReferencesToCollection(collection, collection.getUserMappingId());
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

    @GetMapping(path="/user/{userId}/medium/{mediumId}")
    ResponseEntity<List<ReducedCollectionDto>> getCollectionsByUserWithUnusedMedia(@PathVariable Long userId, @PathVariable Long mediumId) throws Exception {
        Optional<Medium> medium = mediaRepository.findById(mediumId);

        if(medium.isPresent()) {
            Set<Collection> collections = collectionService.removeCollectionsWithMediaId(collectionRepository.findAllByUserId(userId), mediumId);

            return ResponseEntity.ok(
                    collections.stream()
                            .map(collectionService::convertToReducedDto)
                            .collect(Collectors.toList())
            );
        }
        throw new Exception("Tja");

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


    @PutMapping(consumes="application/json", produces="application/json")
    ResponseEntity<ReducedCollectionDto> update(@RequestBody Collection collection) throws CollectionNotFoundException {
        Optional<Collection> targetCollection = collectionRepository.findById(collection.getId());
        if(targetCollection.isPresent()) {
            targetCollection.get().setTitle(collection.getTitle());
            Collection savedCollection = collectionRepository.save(targetCollection.get());
            return ResponseEntity.ok(collectionService.convertToReducedDto(savedCollection));
        }
        throw new CollectionNotFoundException("There is not collection with id " + collection.getId());
    }

    @DeleteMapping(path="/{id}/medium/{mediumId}")
    ResponseEntity<CollectionDto> deleteMediumFromCollection (@PathVariable Long id, @PathVariable Long mediumId) throws CollectionNotFoundException {
        Optional<Collection> targetCollection = collectionRepository.findById(id);
        if(targetCollection.isPresent()) {
            targetCollection.get().getMedia().removeIf(e->e.getId().equals(mediumId));
            Collection savedCollection = collectionRepository.save(targetCollection.get());
            return ResponseEntity.ok(collectionService.convertToDto(savedCollection));
        }
        throw new CollectionNotFoundException("There is no collection with id " + id);
    }

    @PutMapping(path="/{id}/medium/{mediumId}")
    ResponseEntity<CollectionDto> addMediumToCollection(@PathVariable Long id, @PathVariable Long mediumId) throws CollectionNotFoundException {
        Optional<Medium> medium = mediaRepository.findById(mediumId);
        Optional<Collection> collection = collectionRepository.findById(id);

        if(collection.isPresent()) {
            if(medium.isPresent()) {
                collection.get().getMedia().add(medium.get());
                Collection savedCollection = collectionRepository.save(collection.get());
                return ResponseEntity.ok(collectionService.convertToDto(savedCollection));
            }
        }
        throw new CollectionNotFoundException("There is no collection with id " + id);
    }
}
