package de.fourzerofournotfound.rateyourstuff.rays.controllers;


import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.MovieDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Movie;

import de.fourzerofournotfound.rateyourstuff.rays.models.errors.MovieNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.MovieRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.FileUploadService;
import de.fourzerofournotfound.rateyourstuff.rays.services.MediaService;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.DuplicateMediumException;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/movies")
public class MovieController {
    private final MovieRepository movieRepository;
    private final FileUploadService fileUploadService;
    private final PageableService pageableService;
    private final MovieService movieService;
    private final MediaService mediaService;

    @Autowired
    public MovieController(MovieRepository repository, FileUploadService fus, PageableService pageableService, MovieService movieService, MediaService mediaService) {
        this.movieRepository = repository;
        this.fileUploadService = fus;
        this.pageableService = pageableService;
        this.movieService = movieService;
        this.mediaService = mediaService;
    }

    @GetMapping("/all")
    ResponseEntity<List<MovieDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "") String orderBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Pageable pageable = pageableService.createPageable(orderBy, order, page, size);
        List<Movie> movies = this.movieRepository.findAll(pageable).getContent();

        return ResponseEntity.ok(
                movies.stream()
                        .map(movieService::convertToDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    ResponseEntity<MovieDto> getById (@PathVariable Long id) throws MovieNotFoundException {
        Optional<Movie> movie = this.movieRepository.findById(id);
        if(movie.isPresent()) {
            MovieDto movieDto = movieService.convertToDto(movie.get());
            return ResponseEntity.ok(movieDto);
        } else {
            throw new MovieNotFoundException("No Movie found for id " + id);
        }
    }

    @GetMapping()
    ResponseEntity<MovieDto> findByTitle(@RequestParam(value="title") String title) throws MovieNotFoundException {
        Optional<Movie> movie = this.movieRepository.findByMediumName(title);
        if(movie.isPresent()) {
            MovieDto movieDto = movieService.convertToDto(movie.get());
            return ResponseEntity.ok(movieDto);
        } else {
            throw new MovieNotFoundException("No Movie with title " +title );
        }
    }

    @PostMapping(path="/add", consumes= "application/json", produces="application/json")
    ResponseEntity<Movie> add(@RequestBody Movie movie) throws DuplicateMediumException {
        if(this.mediaService.isValidMovie(movie)) {
            this.movieRepository.save(movie);
            movie.setGenres(this.mediaService.getGenresSet(movie.getGenreStrings(), movie));
            movie.setLanguages(this.mediaService.getLanguageSet(movie.getLanguageStrings(), movie));
            return ResponseEntity.ok(this.movieRepository.save(movie));
        } else {
            throw new DuplicateMediumException("The Movie " + movie.getMediumName() + " already exists.");
        }

    }

    @PutMapping(consumes="application/json", produces="application/json")
    ResponseEntity<Movie> update(@RequestBody Movie movie) {
        return ResponseEntity.ok(this.movieRepository.save(movie));
    }

    @DeleteMapping("/{id}")
    void deleteMovie (@PathVariable Long id) {
        this.movieRepository.deleteById(id);
    }

    @PostMapping("/images/{id}")
    ResponseEntity<Movie> addImage(@RequestParam(name="image") MultipartFile multipartFile, @PathVariable Long id) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        Optional<Movie> movie = this.movieRepository.findById(id);
        //check if the given movie exists
        if(movie.isPresent()) {
            movie.get().setPicturePath(movie.get().getId() + "/" + fileName);
            //define the target path
            String uploadDir = Movie.IMAGE_PATH_PREFIX + id;
            //upload the file
            fileUploadService.saveFile(uploadDir, fileName, multipartFile);
            return ResponseEntity.ok(this.movieRepository.save(movie.get()));
        }
        return ResponseEntity.badRequest().build();
    }

}