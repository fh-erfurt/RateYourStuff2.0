package de.fourzerofournotfound.rateyourstuff.rays.services;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.RatingDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Medium;
import de.fourzerofournotfound.rateyourstuff.rays.models.Rating;
import de.fourzerofournotfound.rateyourstuff.rays.models.User;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.MediaRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.UserRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.InvalidRatingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("rs")
public class RatingService {
    private final UserRepository userRepository;
    private final MediaRepository mediaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RatingService(UserRepository userRepository,
                         MediaRepository mediaRepository,
                         ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.mediaRepository = mediaRepository;
        this.modelMapper = modelMapper;
    }

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
        return modelMapper.map(rating, RatingDto.class);
    }
}
