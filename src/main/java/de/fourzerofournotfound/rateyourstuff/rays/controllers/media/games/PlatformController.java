package de.fourzerofournotfound.rateyourstuff.rays.controllers.media.games;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.games.PlatformDto;
import de.fourzerofournotfound.rateyourstuff.rays.errors.media.games.PlatformNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.games.Platform;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.games.PlatformRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.games.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>This Controller provides basic REST Interfaces to interact with Platform entities from the database</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@RestController
@RequestMapping("/rest/platforms")
public class PlatformController {

    private final PlatformRepository platformRepository;
    private final PlatformService platformService;
    private final PageableService pageableService;

    @Autowired
    public PlatformController(PlatformRepository repository, PlatformService platformService, PageableService pageableService) {
        this.platformRepository = repository;
        this.platformService = platformService;
        this.pageableService = pageableService;
    }

    /**
     * This Method returns all platforms from the database
     *
     * @param page    the current page (optional)
     * @param size    the number of items per page
     * @param orderBy the attributed that should be ordered
     * @param order   the order (asc, desc)
     * @return a list of PlatformDTOs
     */
    @GetMapping("/all")
    ResponseEntity<List<PlatformDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "") String orderBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Pageable pageable = pageableService.createPageable(orderBy, order, page, size);
        List<Platform> platforms = this.platformRepository.findAll(pageable).getContent();

        return ResponseEntity.ok(platforms.stream()
                .map(platformService::convertToDto)
                .collect(Collectors.toList()));
    }

    /**
     * This method is used to get a single Platform by its id
     *
     * @param id the if of the platform
     * @return the found platform DTO
     * @throws PlatformNotFoundException if there is no platform with the given id
     */
    @GetMapping("/{id}")
    ResponseEntity<PlatformDto> getById(@PathVariable Long id) throws PlatformNotFoundException {
        Optional<Platform> foundPlatform = this.platformRepository.findById(id);
        if (foundPlatform.isPresent()) {
            return ResponseEntity.ok(platformService.convertToDto(foundPlatform.get()));
        }
        throw new PlatformNotFoundException("There is no " + Platform.class.getSimpleName() + " with id " + id);
    }
}