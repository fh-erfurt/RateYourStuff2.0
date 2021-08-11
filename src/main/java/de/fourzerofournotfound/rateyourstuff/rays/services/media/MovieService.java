package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.MovieDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Movie;
import de.fourzerofournotfound.rateyourstuff.rays.models.Network;
import de.fourzerofournotfound.rateyourstuff.rays.models.Rating;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.NetworkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("mos")
public class MovieService {
    private final ModelMapper modelMapper;
    private final NetworkRepository networkRepository;

    @Autowired
    public MovieService(ModelMapper modelMapper, NetworkRepository networkRepository) {
        this.modelMapper = modelMapper;
        this.networkRepository = networkRepository;
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
