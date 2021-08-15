package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.MovieDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Movie;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Network;
import de.fourzerofournotfound.rateyourstuff.rays.models.Rating;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.MovieRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.NetworkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * MovieService
 * <p>This service provides methods to the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.MovieController MovieController}</p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Service("movieService")
public class MovieService {
    private final ModelMapper modelMapper;
    private final NetworkRepository networkRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(ModelMapper modelMapper, NetworkRepository networkRepository, MovieRepository movieRepository) {
        this.modelMapper = modelMapper;
        this.networkRepository = networkRepository;
        this.movieRepository = movieRepository;
    }

    /**
     * Checks if a movie can be stored within the database without causing duplicates
     * @param movie the movie that should be checked
     * @return true if the movie is not already stored in the database
     */
    public boolean isValidMovie(Movie movie)
    {
        Optional<Movie> optionalMovie;
        if(Objects.nonNull(movie.getId())) {
            optionalMovie = movieRepository.findMovieByIdNotAndMediumNameIgnoreCaseAndReleaseDate(movie.getId(), movie.getMediumName(), movie.getReleaseDate());
        } else {
            optionalMovie = movieRepository.findMovieByMediumNameIgnoreCaseAndReleaseDate(movie.getMediumName(), movie.getReleaseDate());
        }
        return optionalMovie.isEmpty();
    }

    /**
     * Converts a given movie to a movieDTO object to limit the data that gets sent to the client
     * @param movie   the movie that should be converted
     * @return          the corresponding dtoObject
     */
    public MovieDto convertToDto(Movie movie) {
        MovieDto movieDto= modelMapper.map(movie, MovieDto.class);
        movieDto.setAverageRating(movie.getMediumRatings());
        movieDto.setNumberOfRatings(movie.getMediumRatings());
        movieDto.setMIN_RATING_POINTS(Rating.MIN_POINTS);
        movieDto.setMAX_RATING_POINTS(Rating.MAX_POINTS);
        movieDto.setNumberOfComments(movie.getComments());
        movieDto.setNumberOfCollections(movie.getCollections());
        return movieDto;
    }

    /**
     * Returns a reference to the given network by its title. Creates the network, if it does not exist.
     * @param networkTitle  the title that should be searched within the database
     * @return              the entity of the network
     */
    public Network getNetwork(String networkTitle) {
        Optional<Network> network = networkRepository.findByNetworkTitle(networkTitle);
        if(network.isPresent()) {
            return network.get();
        } else {
            Network newNetwork = new Network();
            newNetwork.setNetworkTitle(networkTitle);
            return networkRepository.save(newNetwork);
        }
    }
}
