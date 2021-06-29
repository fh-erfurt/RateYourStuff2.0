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
    MediaRepository mediaRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PageableService pageableService;

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
    ResponseEntity<Rating> getById (@PathVariable Long id) throws RatingNotFoundException {
        return ResponseEntity.ok(this.ratingRepository.findById(id).orElseThrow(() -> new RatingNotFoundException("No Rating found for id " + id)));
    }

    @PostMapping(path="/add", consumes= "application/json", produces="application/json")
    ResponseEntity<Rating> add(@RequestBody Rating rating) {
        Optional<Medium> medium = mediaRepository.findById(rating.getMediumMappingId());
        Optional<User> user = userRepository.findById(rating.getUserMappingId());
        if(medium.isPresent() && user.isPresent()) {
            rating.setMedium(medium.get());
            rating.setUser(user.get());
            return ResponseEntity.ok(ratingRepository.save(rating));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping(consumes="application/json", produces="application/json")
    ResponseEntity<Rating> update(@RequestBody Rating rating) {
        return ResponseEntity.ok(this.ratingRepository.save(rating));
    }

    @DeleteMapping("/{id}")
    void deleteSeason (@PathVariable Long id) {
        this.ratingRepository.deleteById(id);
    }

    private RatingDto convertToDto(Rating rating) {
        RatingDto ratingDto = modelMapper.map(rating, RatingDto.class);
        //ratingDto.setUserName(rating.getUser().getUserName());
        return ratingDto;
    }
}
