package de.fourzerofournotfound.rateyourstuff.rays.controllers.media.movies;


import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.movies.MovieDto;
import de.fourzerofournotfound.rateyourstuff.rays.errors.media.movies.MovieNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.movies.Movie;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.movies.MovieRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.FileUploadService;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.errors.media.DuplicateMediumException;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.GenreService;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.LanguageService;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.movies.MovieService;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * <p>This Controller provides basic REST Interfaces to interact with Movie entities from the database</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@RestController
@RequestMapping("/rest/movies")
public class MovieController {
    private final MovieRepository movieRepository;
    private final FileUploadService fileUploadService;
    private final PageableService pageableService;
    private final MovieService movieService;
    private final NetworkService networkService;
    private final LanguageService languageService;
    private final GenreService genreService;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    public MovieController(MovieRepository repository,
                           FileUploadService fus,
                           PageableService pageableService,
                           MovieService movieService,
                           NetworkService networkService,
                           LanguageService languageService,
                           GenreService genreService) {
        this.movieRepository = repository;
        this.fileUploadService = fus;
        this.pageableService = pageableService;
        this.movieService = movieService;
        this.languageService = languageService;
        this.genreService = genreService;
        this.networkService = networkService;
    }

    /**
     * This Method returns all movies from the database
     *
     * @param page    the current page (optional)
     * @param size    the number of items per page
     * @param orderBy the attributed that should be ordered
     * @param order   the order (asc, desc)
     * @return a list of MovieDTOs
     */
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

    /**
     * This method is used to return a single movie by its id
     *
     * @param id the id of the movie that should be searched
     * @return the found MovieDTO
     * @throws MovieNotFoundException if there is no movie with the given id
     */
    @GetMapping("/{id}")
    ResponseEntity<MovieDto> getById(@PathVariable Long id) throws MovieNotFoundException {
        Optional<Movie> movie = this.movieRepository.findById(id);
        if (movie.isPresent()) {
            MovieDto movieDto = movieService.convertToDto(movie.get());
            return ResponseEntity.ok(movieDto);
        } else {
            throw new MovieNotFoundException("No " + Movie.class.getSimpleName() + " found for id " + id);
        }
    }

    /**
     * This method is used to add a new movie to the database
     *
     * @param movie the movie that should be added
     * @return the entity of the newly added movie
     * @throws DuplicateMediumException if there is already the same movie in the database
     */
    @PreAuthorize("hasAuthority('User')")
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    ResponseEntity<MovieDto> add(@RequestBody Movie movie) throws DuplicateMediumException {
        if (this.movieService.isValidMovie(movie)) {
            this.movieRepository.save(movie);
            movie.setGenres(this.genreService.getGenresSet(movie.getGenreStrings()));
            movie.setLanguages(this.languageService.getLanguageSet(movie.getLanguageStrings()));
            movie.setNetwork(this.networkService.getNetwork(movie.getNetworkTitle()));
            Movie savedMovie = this.movieRepository.save(movie);
            logger.info("Added " + Movie.class.getSimpleName() + " with id " + savedMovie.getId());
            return ResponseEntity.ok(movieService.convertToDto(savedMovie));
        } else {
            throw new DuplicateMediumException("The " + Movie.class.getSimpleName() + " " + movie.getMediumName() + " already exists.");
        }
    }

    /**
     * This method is used to update a single movie
     *
     * @param movie the movie that should be updated
     * @return the updated movie
     * @throws DuplicateMediumException if the update would conflict another movie
     */
    @PreAuthorize("hasAuthority('User')")
    @PutMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<MovieDto> update(@RequestBody Movie movie) throws DuplicateMediumException, MovieNotFoundException {
        Optional<Movie> targetMovie = movieRepository.findById(movie.getId());
        if (targetMovie.isPresent()) {
            if (this.movieService.isValidMovie(movie)) {
                movie.setNetwork(this.networkService.getNetwork(movie.getNetworkTitle()));
                this.movieRepository.save(movie);
                movie.setGenres(this.genreService.getGenresSet(movie.getGenreStrings()));
                movie.setLanguages(this.languageService.getLanguageSet(movie.getLanguageStrings()));
                Movie savedMovie = this.movieRepository.save(movie);
                logger.info("Updated " + Movie.class.getSimpleName() + " with id " + movie.getId());
                return ResponseEntity.ok(movieService.convertToDto(savedMovie));
            }
            throw new DuplicateMediumException("The " + Movie.class.getSimpleName() + " " + movie.getMediumName() + " already exists.");
        }
        throw new MovieNotFoundException("There is no " + Movie.class.getSimpleName() + " with id " + movie.getId());
    }


    /**
     * This method is used to attach a poster to a movie
     *
     * @param multipartFile the image that should be uploaded
     * @param id            the id of the movie
     * @return the updated movie
     * @throws IOException            if the upload fails
     * @throws MovieNotFoundException if there is no movie with the given id
     */
    @PreAuthorize("hasAuthority('User')")
    @PostMapping("/images/{id}")
    ResponseEntity<MovieDto> addImage(@RequestParam(name = "image") MultipartFile multipartFile, @PathVariable Long id) throws IOException, MovieNotFoundException {
        String fileName = StringUtils.cleanPath("poster." + fileUploadService.getFileExtension(multipartFile));
        Optional<Movie> movie = this.movieRepository.findById(id);
        //check if the given movie exists
        if (movie.isPresent()) {
            movie.get().setPicturePath(movie.get().getId() + "/" + fileName);
            //define the target path
            String uploadDir = Movie.IMAGE_PATH_PREFIX + id;
            //upload the file
            fileUploadService.saveFile(uploadDir, fileName, multipartFile);

            Movie savedMovie = this.movieRepository.save(movie.get());
            logger.info("Added image \"" + savedMovie.getPicturePath() + "\" for " + Movie.class.getSimpleName() + " with id " + savedMovie.getId());
            return ResponseEntity.ok(movieService.convertToDto(savedMovie));
        }
        throw new MovieNotFoundException("There is no " + Movie.class.getSimpleName() + " with id " + id);
    }

}