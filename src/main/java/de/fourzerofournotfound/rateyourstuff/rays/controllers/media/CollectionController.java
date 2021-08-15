package de.fourzerofournotfound.rateyourstuff.rays.controllers.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.CollectionDto;
import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.ReducedCollectionDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.UserNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.CollectionNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.MediumNotFoundException;
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

/**
 * CollectionController
 * <p>This Controller provides basic REST Interfaces to interact with Collection entities from the database</p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
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

    /**
     * This method adds a new collection to the database
     * @param collection    the collection that should be added
     * @return              the new collection entity that has been added
     * @throws UserNotFoundException    if the user that should be creator of the collection does not exist
     */
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    ResponseEntity<Collection> add(@RequestBody Collection collection) throws UserNotFoundException {
        collection = this.collectionService.addReferencesToCollection(collection, collection.getUserMappingId());
        return ResponseEntity.ok(this.collectionRepository.save(collection));
    }

    /**
     * This Method returns all collections of a given user
     * @param id    the id of the user
     * @param page  the current page
     * @param size  the number of items per page
     * @param orderBy   the attribute that should be ordered by
     * @param order     the order criteria (asc, desc)
     * @return  a list of found CollectionDTOs
     */
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

    /**
     * This method is used to return all collections in which a certain medium is included
     * @param id    the id of the medium
     * @param page  the current page
     * @param size  the number of items per page
     * @param orderBy   the attribute that should be ordered by
     * @param order     the order criteria (asc, desc)
     * @return      a list of all collections of the medium
     */
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

    /**
     * This method is used to get all collections of a user that do not contain a certain medium
     * @param userId    the user id for which all collections should be searched
     * @param mediumId  the id of the medium that should not be included
     * @return          the list of found collections
     * @throws MediumNotFoundException if there is no medium with the given id
     */
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
        throw new MediumNotFoundException("There is no medium with id " + mediumId);

    }

    /**
     * This method returns the collection that belongs to the given id
     * @param id    the id of the collection that should be searched
     * @return      the found collection
     * @throws CollectionNotFoundException  if there is no collection with the given id
     */
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


    /**
     * This method is used to update a given collection
     * @param collection    the collection that should be updated
     * @return              the updated collection
     * @throws CollectionNotFoundException  if there is no colelction that matches the given id
     */
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

    /**
     * This method is used to remove a medium from a collection
     * @param collectionId      the id of the collection that should be updated
     * @param mediumId          the id of the medium that should be removed from the collection
     * @return                  the updated
     * @throws CollectionNotFoundException  if there is no collection with the given id
     */
    @DeleteMapping(path="/{collectionId}/medium/{mediumId}")
    ResponseEntity<CollectionDto> deleteMediumFromCollection (@PathVariable Long collectionId, @PathVariable Long mediumId) throws CollectionNotFoundException {
        Optional<Collection> targetCollection = collectionRepository.findById(collectionId);
        if(targetCollection.isPresent()) {
            targetCollection.get().getMedia().removeIf(e->e.getId().equals(mediumId));
            Collection savedCollection = collectionRepository.save(targetCollection.get());
            return ResponseEntity.ok(collectionService.convertToDto(savedCollection));
        }
        throw new CollectionNotFoundException("There is no collection with id " + collectionId);
    }

    /**
     * This method is used to add a medium to a collection
     * @param collectionId  the id of the collection that should be updated
     * @param mediumId      the id of the medium that should be added
     * @return              the updated collection
     * @throws CollectionNotFoundException  if there is no collection with the given id
     */
    @PutMapping(path="/{collectionId}/medium/{mediumId}")
    ResponseEntity<CollectionDto> addMediumToCollection(@PathVariable Long collectionId, @PathVariable Long mediumId) throws CollectionNotFoundException {
        Optional<Medium> medium = mediaRepository.findById(mediumId);
        Optional<Collection> collection = collectionRepository.findById(collectionId);

        if(collection.isPresent()) {
            if(medium.isPresent()) {
                collection.get().getMedia().add(medium.get());
                Collection savedCollection = collectionRepository.save(collection.get());
                return ResponseEntity.ok(collectionService.convertToDto(savedCollection));
            }
        }
        throw new CollectionNotFoundException("There is no collection with id " + collectionId);
    }
}
