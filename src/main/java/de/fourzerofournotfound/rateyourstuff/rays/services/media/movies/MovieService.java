package de.fourzerofournotfound.rateyourstuff.rays.services.media.movies;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.media.movies.MovieController;
import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.movies.MovieDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Rating;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.movies.Movie;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.movies.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * <p>This service provides methods to the {@link MovieController MovieController}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Service("movieService")
public class MovieService {
    private final ModelMapper modelMapper;
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(ModelMapper modelMapper,
                        MovieRepository movieRepository) {
        this.modelMapper = modelMapper;
        this.movieRepository = movieRepository;
    }

    /**
     * Checks if a movie can be stored within the database without causing duplicates
     *
     * @param movie the movie that should be checked
     * @return true if the movie is not already stored in the database
     */
    public boolean isValidMovie(Movie movie) {
        Optional<Movie> optionalMovie;
        if (Objects.nonNull(movie.getId())) {
            optionalMovie = movieRepository.findMovieByIdNotAndMediumNameIgnoreCaseAndReleaseDate(movie.getId(), movie.getMediumName(), movie.getReleaseDate());
        } else {
            optionalMovie = movieRepository.findMovieByMediumNameIgnoreCaseAndReleaseDate(movie.getMediumName(), movie.getReleaseDate());
        }
        return optionalMovie.isEmpty();
    }

    /**
     * Converts a given movie to a movieDTO object to limit the data that gets sent to the client
     *
     * @param movie the movie that should be converted
     * @return the corresponding dtoObject
     */
    public MovieDto convertToDto(Movie movie) {
        MovieDto movieDto = modelMapper.map(movie, MovieDto.class);
        movieDto.setAverageRating(movie.getMediumRatings());
        movieDto.setNumberOfRatings(movie.getMediumRatings());
        movieDto.setMIN_RATING_POINTS(Rating.MIN_POINTS);
        movieDto.setMAX_RATING_POINTS(Rating.MAX_POINTS);
        movieDto.setNumberOfComments(movie.getComments());
        movieDto.setNumberOfCollections(movie.getCollections());
        return movieDto;
    }


}
