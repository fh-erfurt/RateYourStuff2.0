package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.RatingDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Rating;
import de.fourzerofournotfound.rateyourstuff.rays.errors.RatingNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.RatingRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.RatingService;
import de.fourzerofournotfound.rateyourstuff.rays.errors.InvalidRatingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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

    @GetMapping("/{id}")
    ResponseEntity<RatingDto> getById(@PathVariable Long id) throws RatingNotFoundException {
        Optional<Rating> rating = ratingRepository.findById(id);
        if (rating.isPresent()) {
            return ResponseEntity.ok(ratingService.convertToDto(rating.get()));
        } else {
            throw new RatingNotFoundException("No " + Rating.class.getSimpleName() + " found for id " + id);
        }
    }

    @GetMapping("/user/{userId}/medium/{mediumId}")
    ResponseEntity<RatingDto> getByUserIdAndByMediumId(@PathVariable Long userId, @PathVariable Long mediumId) throws RatingNotFoundException {
        Optional<Rating> rating = ratingRepository.findByMediumIdAndUserId(mediumId, userId);
        if(rating.isPresent()) {
            return ResponseEntity.ok(ratingService.convertToDto(rating.get()));
        }
        throw new RatingNotFoundException("There is no " + Rating.class.getSimpleName() + " for userId " + userId + " and mediumId " + mediumId);
    }


    @PreAuthorize("hasAuthority('User')")
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    ResponseEntity<RatingDto> add(@RequestBody Rating rating) throws InvalidRatingException {
        Optional<Rating> foundRating = ratingRepository.findByMediumIdAndUserId(rating.getMediumMappingId(), rating.getUserMappingId());
        //check if there is already a rating from the same user for the same medium
        if(foundRating.isPresent()) {
            //set the id of the incoming rating to make sure that is gets overwritten
            rating.setId(foundRating.get().getId());
        }
        rating = ratingService.addReferencesToRating(rating);
        rating = ratingService.validateRatingValue(rating);
        logger.info("Added " + Rating.class.getSimpleName() + " with id " + rating.getId());
        Rating savedRating = ratingRepository.save(rating);
        return ResponseEntity.ok(ratingService.convertToDto(savedRating));
    }


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

    @PreAuthorize("hasAuthority('User')")
    @DeleteMapping("/{id}")
    void deleteRating(@PathVariable Long id) {
        this.ratingRepository.deleteById(id);
    }

    @GetMapping("/count/{mediumId}")
    ResponseEntity<Long> countRatings(@PathVariable Long mediumId) {
        return ResponseEntity.ok(this.ratingRepository.countAllByMediumId(mediumId));
    }
}
