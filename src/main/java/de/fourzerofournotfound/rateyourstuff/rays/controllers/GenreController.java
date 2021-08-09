package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.GenreDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Genre;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.GenreRepository;
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
