package de.fourzerofournotfound.rateyourstuff.rays.controllers;


import de.fourzerofournotfound.rateyourstuff.rays.models.BookPublisher;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.BookPublisherNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.BookPublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookPublishers-rest")
public class BookPublisherController {

    final
    BookPublisherRepository bookPublisherRepository;

    @Autowired
    public BookPublisherController(BookPublisherRepository bookPublisherRepository) {
        this.bookPublisherRepository = bookPublisherRepository;
    }

    @GetMapping("/all")
    ResponseEntity<List<BookPublisher>> getAll() {
        return ResponseEntity.ok(this.bookPublisherRepository.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<BookPublisher> getById(@PathVariable Long id) throws BookPublisherNotFoundException {
        return ResponseEntity.ok(this.bookPublisherRepository.findById(id).orElseThrow(() -> new BookPublisherNotFoundException("No Publisher found for id " + id))); }

    @GetMapping()
    ResponseEntity<BookPublisher> findByTitle(@RequestParam(value = "title") String title) throws BookPublisherNotFoundException {
        return ResponseEntity.ok(this.bookPublisherRepository.findByBookPublisherTitle(title).orElseThrow(() -> new BookPublisherNotFoundException("No Publisher with title " + title))); }

    @PostMapping(path="/add", consumes= "application/json", produces="application/json")
    ResponseEntity<BookPublisher> add(@RequestBody BookPublisher bookPublisher) {
        return ResponseEntity.ok(this.bookPublisherRepository.save(bookPublisher));
    }

    @PutMapping(consumes="application/json", produces="application/json")
    ResponseEntity<BookPublisher> update(@RequestBody BookPublisher bookPublisher) {
        return ResponseEntity.ok(this.bookPublisherRepository.save(bookPublisher));
    }

    @DeleteMapping("/{id}")
    void deletePublisher (@PathVariable Long id) {
        this.bookPublisherRepository.deleteById(id);
    }
}