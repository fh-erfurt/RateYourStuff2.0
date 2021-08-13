package de.fourzerofournotfound.rateyourstuff.rays.controllers.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.MediumDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Medium;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.MediaRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping(path = "/collection/{id}")
    ResponseEntity<List<MediumDto>> getAllByCollection(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "") String orderBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Pageable pageable = pageableService.createPageable(orderBy, order, page, size);
        List<Medium> media = this.mediaRepository.findAllByCollectionsId(id, pageable).getContent();

        return ResponseEntity.ok(
                media.stream()
                        .map(mediaService::convertToDto)
                        .collect(Collectors.toList())
        );
    }
}