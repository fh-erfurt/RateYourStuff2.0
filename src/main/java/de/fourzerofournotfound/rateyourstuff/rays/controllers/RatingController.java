package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.RatingDto;
import de.fourzerofournotfound.rateyourstuff.rays.errors.InvalidRatingException;
import de.fourzerofournotfound.rateyourstuff.rays.errors.RatingNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.Rating;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.RatingRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * <p>This Controller provides basic REST Interfaces to interact with Rating entities from the database</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@RestController
@RequestMapping("/rest/ratings")
public class RatingController {

    private final RatingRepository ratingRepository;
    private final PageableService pageableService;
    private final RatingService ratingService;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    public RatingController(RatingRepository ratingRepository, PageableService pageableService, RatingService ratingService) {
        this.ratingRepository = ratingRepository;
        this.pageableService = pageableService;
        this.ratingService = ratingService;
    }

    /**
     * This method is used to get all ratings from the database
     *
     * @param page    the current page (optional)
     * @param size    the number of items per page
     * @param orderBy the attributed that should be ordered
     * @param order   the order (asc, desc)
     * @return a list of rating dtos
     */
    @GetMapping("/all")
    ResponseEntity<List<RatingDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "createdAt") String orderBy,
            @RequestParam(defaultValue = "desc") String order
    ) {
        Pageable pageable = pageableService.createPageable(orderBy, order, page, size);
        List<Rating> ratings = ratingRepository.findAll(pageable).getContent();
        return ResponseEntity.ok(
                ratings.stream()
                        .map(ratingService::convertToDto)
                        .collect(Collectors.toList())
        );
    }

    /**
     * This method is used to get all ratings for a specific medium from the database
     *
     * @param page     the current page (optional)
     * @param size     the number of items per page
     * @param orderBy  the attributed that should be ordered
     * @param order    the order (asc, desc)
     * @param mediumId the id of the medium that should be searched
     * @return a list of rating dtos
     */
    @GetMapping("/all/medium/{mediumId}")
    ResponseEntity<List<RatingDto>> getAllByMediumId(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "createdAt") String orderBy,
            @RequestParam(defaultValue = "desc") String order,
            @PathVariable Long mediumId
    ) {
        Pageable pageable = pageableService.createPageable(orderBy, order, page, size);
        List<Rating> ratings = ratingRepository.findAllByMediumId(mediumId, pageable).getContent();
        return ResponseEntity.ok(
                ratings.stream()
                        .map(ratingService::convertToDto)
                        .collect(Collectors.toList())
        );
    }

    /**
     * This method is used to get all ratings with a specific user id from the database
     *
     * @param page    the current page (optional)
     * @param size    the number of items per page
     * @param orderBy the attributed that should be ordered
     * @param order   the order (asc, desc)
     * @param userId  the id of the user that should be searched
     * @return a list of rating dtos
     */
    @GetMapping("/all/user/{userId}")
    ResponseEntity<List<RatingDto>> getAllByUserId(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "createdAt") String orderBy,
            @RequestParam(defaultValue = "desc") String order,
            @PathVariable Long userId
    ) {
        Pageable pageable = pageableService.createPageable(orderBy, order, page, size);
        List<Rating> ratings = ratingRepository.findAllByUserId(userId, pageable).getContent();
        return ResponseEntity.ok(
                ratings.stream()
                        .map(ratingService::convertToDto)
                        .collect(Collectors.toList())
        );
    }

    /**
     * This method is used to get a specific rating by its id from the database
     *
     * @param id the id of the rating
     * @return a dto of the found rating
     * @throws RatingNotFoundException if there is no rating with the given id
     */
    @GetMapping("/{id}")
    ResponseEntity<RatingDto> getById(@PathVariable Long id) throws RatingNotFoundException {
        Optional<Rating> rating = ratingRepository.findById(id);
        if (rating.isPresent()) {
            return ResponseEntity.ok(ratingService.convertToDto(rating.get()));
        } else {
            throw new RatingNotFoundException("No " + Rating.class.getSimpleName() + " found for id " + id);
        }
    }

    /**
     * This method is used to get a rating from a specific user for a specific medium from the database
     *
     * @param userId   the id of the user that is linked to the rating
     * @param mediumId the id of the medium that is linked to the rating
     * @return a dto of the found rating
     * @throws RatingNotFoundException if there is no rating for the given user and medium
     */
    @GetMapping("/user/{userId}/medium/{mediumId}")
    ResponseEntity<RatingDto> getByUserIdAndByMediumId(@PathVariable Long userId, @PathVariable Long mediumId) throws RatingNotFoundException {
        Optional<Rating> rating = ratingRepository.findByMediumIdAndUserId(mediumId, userId);
        if (rating.isPresent()) {
            return ResponseEntity.ok(ratingService.convertToDto(rating.get()));
        }
        throw new RatingNotFoundException("There is no " + Rating.class.getSimpleName() + " for userId " + userId + " and mediumId " + mediumId);
    }


    /**
     * This method is used to add a new rating to the database.
     * In order to prevent duplicate ratings in the database, this method automatically checks if there is already a rating with the same user id and medium id.
     * If there is already an existing rating, it will be updated
     *
     * @param rating the rating that should be added to the database
     * @return a dto of the newly added (or updated, if it already existed) rating
     * @throws InvalidRatingException if the rating contains invalid values
     */
    @PreAuthorize("hasAuthority('User')")
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    ResponseEntity<RatingDto> add(@RequestBody Rating rating) throws InvalidRatingException {
        Optional<Rating> foundRating = ratingRepository.findByMediumIdAndUserId(rating.getMediumMappingId(), rating.getUserMappingId());
        //check if there is already a rating from the same user for the same medium
        if (foundRating.isPresent()) {
            //set the id of the incoming rating to make sure that is gets overwritten
            rating.setId(foundRating.get().getId());
        }
        rating = ratingService.addReferencesToRating(rating);
        rating = ratingService.validateRatingValue(rating);
        logger.info("Added " + Rating.class.getSimpleName() + " with id " + rating.getId());
        Rating savedRating = ratingRepository.save(rating);
        return ResponseEntity.ok(ratingService.convertToDto(savedRating));
    }


    /**
     * This method is used to update an existing rating. It updates the description and the given points of the rating
     *
     * @param rating the rating that should be updated
     * @return a dto of the saved rating
     * @throws RatingNotFoundException if there is no rating with the given rating id
     */
    @PreAuthorize("hasAuthority('User')")
    @PutMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<RatingDto> update(@RequestBody Rating rating) throws RatingNotFoundException {
        Optional<Rating> foundRating = ratingRepository.findById(rating.getId());
        if (foundRating.isPresent()) {
            Rating ratingToSave = foundRating.get();
            ratingToSave.setDescription(rating.getDescription());
            ratingToSave.setGivenPoints(rating.getGivenPoints());
            ratingToSave = ratingService.validateRatingValue(ratingToSave);
            logger.info("Updated " + Rating.class.getSimpleName() + " width id " + ratingToSave.getId());
            Rating savedRating = this.ratingRepository.save(ratingToSave);
            return ResponseEntity.ok(ratingService.convertToDto(savedRating));
        }
        throw new RatingNotFoundException("No " + Rating.class.getSimpleName() + " found with id " + rating.getId());
    }

    /**
     * Deletes the given rating
     *
     * @param id the id of the rating that should be deleted
     * @return http status 200
     * @throws RatingNotFoundException if there is no rating with the given id
     */
    @PreAuthorize("hasAuthority('User')")
    @DeleteMapping("/{id}")
    HttpStatus deleteRating(@PathVariable Long id) throws RatingNotFoundException {
        Optional<Rating> rating = ratingRepository.findById(id);
        if (rating.isPresent()) {
            this.ratingRepository.deleteById(id);
            logger.info("Removed " + Rating.class.getSimpleName() + " with id " + id);
            return HttpStatus.OK;
        }
        throw new RatingNotFoundException("There is no " + Rating.class.getSimpleName() + " with id " + id);
    }

    /**
     * This Method is used to count the number of ratings for a certain medium
     *
     * @param mediumId the id of the medium for which ratings should be counted
     * @return the number of ratings for the given medium
     */
    @GetMapping("/count/{mediumId}")
    ResponseEntity<Long> countRatings(@PathVariable Long mediumId) {
        return ResponseEntity.ok(this.ratingRepository.countAllByMediumId(mediumId));
    }
}
