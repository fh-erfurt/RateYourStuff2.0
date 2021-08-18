package de.fourzerofournotfound.rateyourstuff.rays.controllers.media;


import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.MediumDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Medium;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


/**
 * <p> This controller ensures that the input is passed on from the front end to the function in the back end and vice versa.</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
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
        List<Medium> searchResults = mediaService.getSearchResult(s);
        return ResponseEntity.ok(searchResults.stream().map(mediaService::convertToDto).collect(Collectors.toList()));
    }
}
