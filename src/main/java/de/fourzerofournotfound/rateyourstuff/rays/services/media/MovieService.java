package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.MovieDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Movie;
import de.fourzerofournotfound.rateyourstuff.rays.models.Rating;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mos")
public class MovieService {
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Converts a given movie to a movieDTO object to limit the data that gets sent to the client
     * @param movie   the movie that should be converted
     * @return          the corresponding dtoObject
     */
    public MovieDto convertToDto(Movie movie) {
        MovieDto movieDto= modelMapper.map(movie, MovieDto.class);
        movieDto.setAverageRating(movie.getMediumRatings());
        movieDto.setNumberOfRatings(movie.getMediumRatings().size());
        movieDto.setMIN_RATING_POINTS(Rating.MIN_POINTS);
        movieDto.setMAX_RATING_POINTS(Rating.MAX_POINTS);
        movieDto.setNumberOfComments(movie.getComments().size());
        movieDto.setNumberOfCollections(movie.getCollections().size());
        return movieDto;
    }
}
