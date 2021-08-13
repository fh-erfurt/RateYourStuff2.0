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

@Service("mos")
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
     * This service is used to check if a given movie-object(checked by its attributes) is already stored in database
     * @param movie - object which is streamed via rest api
     * @return true if a object is already stored in database (the entry of this movie-object is valid)
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

    public Network getNetwork(String networkTitle, Movie movie) {
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
