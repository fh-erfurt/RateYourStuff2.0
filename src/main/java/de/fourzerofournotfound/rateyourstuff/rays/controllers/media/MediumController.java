package de.fourzerofournotfound.rateyourstuff.rays.controllers.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.MediumDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.MediumNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Medium;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.MediaRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Medium Controller
 * <p>This Controller provides basic REST Interfaces to interact with Medium entities from the database</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@RestController
@RequestMapping("/rest/media/")
public class MediumController {
    private final PageableService pageableService;
    private final MediaRepository mediaRepository;
    private final MediaService mediaService;

    @Autowired
    public MediumController(PageableService pageableService, MediaRepository mediaRepository, MediaService mediaService) {
        this.pageableService = pageableService;
        this.mediaRepository = mediaRepository;
        this.mediaService = mediaService;
    }

    /**
     * This Method is used to list all media that belongs to a given Collection
     *
     * @param collectionId the id of the collection
     * @param page         the current page (optional)
     * @param size         the number of items per page
     * @param orderBy      the attributed that should be ordered
     * @param order        the order (asc, desc)
     * @return a list of found GameDTOs
     */
    @GetMapping(path = "/collection/{collectionId}")
    ResponseEntity<List<MediumDto>> getAllByCollection(
            @PathVariable Long collectionId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "") String orderBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Pageable pageable = pageableService.createPageable(orderBy, order, page, size);
        List<Medium> media = this.mediaRepository.findAllByCollectionsId(collectionId, pageable).getContent();

        return ResponseEntity.ok(
                media.stream()
                        .map(mediaService::convertToDto)
                        .collect(Collectors.toList())
        );
    }

    /**
     * This method is used to return a single medium
     *
     * @param id the id of the medium that should be returned
     * @return the found medium
     * @throws MediumNotFoundException if no medium was found
     */
    @GetMapping(path = "/{id}")
    ResponseEntity<MediumDto> getOne(@PathVariable Long id) throws MediumNotFoundException {
        Optional<Medium> medium = mediaRepository.findById(id);
        if (medium.isPresent()) {
            return ResponseEntity.ok(mediaService.convertToDto(medium.get()));
        }
        throw new MediumNotFoundException("There is no " + Medium.class.getSimpleName() + " for id " + id);
    }
}