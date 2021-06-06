package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.models.Comment;
import de.fourzerofournotfound.rateyourstuff.rays.models.Season;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.CommentNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.SeasonNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments-rest")
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/all")
    ResponseEntity<List<Comment>> getAll() {
        return ResponseEntity.ok(this.commentRepository.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Comment> getById (@PathVariable Long id) throws CommentNotFoundException {
        return ResponseEntity.ok(this.commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("No Comment found for id " + id)));
    }

    @PostMapping(path="/add", consumes= "application/json", produces="application/json")
    ResponseEntity<Comment> add(@RequestBody Comment comment) {
        return ResponseEntity.ok(this.commentRepository.save(comment));
    }

    @PutMapping(consumes="application/json", produces="application/json")
    ResponseEntity<Comment> update(@RequestBody Comment comment) {
        return ResponseEntity.ok(this.commentRepository.save(comment));
    }

    @DeleteMapping("/{id}")
    void deleteSeason (@PathVariable Long id) {
        this.commentRepository.deleteById(id);
    }


}
