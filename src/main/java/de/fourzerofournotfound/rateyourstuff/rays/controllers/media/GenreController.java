package de.fourzerofournotfound.rateyourstuff.rays.controllers.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.GenreDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Genre;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.GenreRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.GenreService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>This Controller provides basic REST Interfaces to interact with genre entities from the database</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@RestController
@RequestMapping("/rest/genres")
public class GenreController {
    private final GenreRepository genreRepository;
    private final PageableService pageableService;
    private final GenreService genreService;

    public GenreController(GenreRepository genreRepository, PageableService pageableService, GenreService genreService) {
        this.genreRepository = genreRepository;
        this.pageableService = pageableService;
        this.genreService = genreService;
    }

    /**
     * This Method returns all genres from the database
     *
     * @param page    the current page (optional)
     * @param size    the number of items per page
     * @param orderBy the attributed that should be ordered
     * @param order   the order (asc, desc)
     * @return a list of GenreDTOs
     */
    @GetMapping("/all")
    ResponseEntity<List<GenreDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "") String orderBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Pageable pageable = pageableService.createPageable(orderBy, order, page, size);
        List<Genre> genres = this.genreRepository.findAll(pageable).getContent();

        return ResponseEntity.ok(genres.stream()
                .map(genreService::convertToDto)
                .collect(Collectors.toList()));
    }

}
