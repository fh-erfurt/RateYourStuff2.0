package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.RatingDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Rating;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.RatingNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.RatingRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.RatingService;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.InvalidRatingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/ratings")
public class RatingController {

    private final RatingRepository ratingRepository;
    private final PageableService pageableService;
    private final RatingService ratingService;

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
    ResponseEntity<RatingDto> getById (@PathVariable Long id) throws RatingNotFoundException {
        Optional<Rating> rating = ratingRepository.findById(id);
        if(rating.isPresent()) {
            return ResponseEntity.ok(ratingService.convertToDto(rating.get()));
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


}
