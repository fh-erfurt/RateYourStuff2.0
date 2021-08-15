package de.fourzerofournotfound.rateyourstuff.rays.controllers.media;


import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.MediumDto;
import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.MovieDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Medium;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Movie;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/searchResults")
public class SearchController {
    private final MediaService mediaService;

    @Autowired
    public SearchController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @CrossOrigin("*")
    @GetMapping()
    ResponseEntity<List<MediumDto>> getMatchingMediaList(
            @RequestParam String s
    ) {
        System.out.println(s);
        List<Medium> searchResults = mediaService.getSearchResult(s);
        return ResponseEntity.ok(searchResults.stream()
                                    .map(mediaService::convertToDto)
                                    .collect(Collectors.toList()));
    }
}
