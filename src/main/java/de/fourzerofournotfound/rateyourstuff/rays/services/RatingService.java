package de.fourzerofournotfound.rateyourstuff.rays.services;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.RatingDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Medium;
import de.fourzerofournotfound.rateyourstuff.rays.models.Rating;
import de.fourzerofournotfound.rateyourstuff.rays.models.User;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.MediaRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.RatingRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.UserRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.InvalidRatingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("rs")
public class RatingService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Limits the given Points of a given Rating to the defined minimum and maximum
     * @param rating    the rating object that should be corrected
     * @return          the rating object with a "givenPoints" value between the minimum and the maximum of points that can be given
     */
    public Rating validateRatingValue (Rating rating) {
        Integer givenPoints = rating.getGivenPoints();
        givenPoints = Math.min(givenPoints, Rating.MAX_POINTS);
        givenPoints = Math.max(givenPoints, Rating.MIN_POINTS);
        rating.setGivenPoints(givenPoints);
        return rating;
    }

    /**
     * Ads references to the responding medium and to the user to the rating object
     * @param rating    the rating object that should be given references
     * @return          the rating object with valid references to medium and user
     * @throws InvalidRatingException   if  mediumId or userId are invalid
     */
    public Rating addReferencesToRating(Rating rating) throws InvalidRatingException {
        Optional<Medium> medium = mediaRepository.findById(rating.getMediumMappingId());
        Optional<User> user = userRepository.findById(rating.getUserMappingId());
        if(medium.isPresent() && user.isPresent()) {
            rating.setMedium(medium.get());
            rating.setUser(user.get());
            return rating;
        } else {
            throw new InvalidRatingException("The given rating must have a valid mediumId and a valid userId");
        }
    }

    /**
     * Converts a given rating to a ratingDTO object to limit the data that gets sent to the client
     * @param rating    the rating that should be converted
     * @return          the corresponding dtoObject
     */
    public RatingDto convertToDto(Rating rating) {
        RatingDto ratingDto = modelMapper.map(rating, RatingDto.class);
        ratingDto.setMAX_POINTS(Rating.MAX_POINTS);
        ratingDto.setMIN_POINTS(Rating.MIN_POINTS);
        return ratingDto;
    }
}
