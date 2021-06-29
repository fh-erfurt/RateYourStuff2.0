package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.models.Comment;
import de.fourzerofournotfound.rateyourstuff.rays.models.Medium;
import de.fourzerofournotfound.rateyourstuff.rays.models.Rating;
import de.fourzerofournotfound.rateyourstuff.rays.models.User;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.CommentNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.RatingNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.MediaRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.RatingRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

@RestController
@RequestMapping("/rest/ratings")
public class RatingController {

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    MediaRepository mediaRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    ResponseEntity<List<Rating>> getAll() {
        return ResponseEntity.ok(this.ratingRepository.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Rating> getById (@PathVariable Long id) throws RatingNotFoundException {
        return ResponseEntity.ok(this.ratingRepository.findById(id).orElseThrow(() -> new RatingNotFoundException("No Rating found for id " + id)));
    }

    @PostMapping(path="/add", consumes= "application/json", produces="application/json")
    ResponseEntity<Rating> add(@RequestBody Rating rating) {
        Optional<Medium> medium = mediaRepository.findById(rating.getMediumMappingID());
        Optional<User> user = userRepository.findById(rating.getUserMappingID());
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
}
