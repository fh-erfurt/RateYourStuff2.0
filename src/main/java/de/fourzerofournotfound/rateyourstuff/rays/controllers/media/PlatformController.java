package de.fourzerofournotfound.rateyourstuff.rays.controllers.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.PlatformDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Platform;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.PlatformNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.PlatformRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/{id}")
    ResponseEntity<Platform> getById(@PathVariable Long id) throws PlatformNotFoundException {
        return ResponseEntity.ok(this.platformRepository.findById(id).orElseThrow(() -> new PlatformNotFoundException("No Platform found for id " + id))); }
}