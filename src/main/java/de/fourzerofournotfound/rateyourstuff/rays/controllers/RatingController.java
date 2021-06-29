package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.RatingDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Comment;
import de.fourzerofournotfound.rateyourstuff.rays.models.Medium;
import de.fourzerofournotfound.rateyourstuff.rays.models.Rating;
import de.fourzerofournotfound.rateyourstuff.rays.models.User;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.CommentNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.RatingNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.MediaRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.RatingRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.UserRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.RatingService;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.InvalidRatingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/ratings")
public class RatingController {

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PageableService pageableService;

    @Autowired
    private RatingService ratingService;

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
                .map(this::convertToDto)
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
                        .map(this::convertToDto)
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
                        .map(this::convertToDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    ResponseEntity<RatingDto> getById (@PathVariable Long id) throws RatingNotFoundException {
        Optional<Rating> rating = ratingRepository.findById(id);
        if(rating.isPresent()) {
            return ResponseEntity.ok(convertToDto(rating.get()));
        } else {
            throw new RatingNotFoundException("No Rating found for id " + id);
        }
    }

    @PostMapping(path="/add", consumes= "application/json", produces="application/json")
    ResponseEntity<Rating> add(@RequestBody Rating rating) throws InvalidRatingException {
        rating = ratingService.addReferencesToRating(rating);
        rating = ratingService.validateRatingValue(rating);
        return ResponseEntity.ok(ratingRepository.save(rating));
    }

    @PutMapping(consumes="application/json", produces="application/json")
    ResponseEntity<Rating> update(@RequestBody Rating rating) {
        return ResponseEntity.ok(this.ratingRepository.save(rating));
    }

    @DeleteMapping("/{id}")
    void deleteRating (@PathVariable Long id) {
        this.ratingRepository.deleteById(id);
    }

    /**
     * Converts a given rating to a ratingDTO object to limit the data that gets sent to the client
     * @param rating    the rating that should be converted
     * @return          the corresponding dtoObject
     */
    private RatingDto convertToDto(Rating rating) {
        RatingDto ratingDto = modelMapper.map(rating, RatingDto.class);
        ratingDto.setMAX_POINTS(Rating.MAX_POINTS);
        ratingDto.setMIN_POINTS(Rating.MIN_POINTS);
        return ratingDto;
    }
}
