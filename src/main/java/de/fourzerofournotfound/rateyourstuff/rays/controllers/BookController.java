package de.fourzerofournotfound.rateyourstuff.rays.controllers;


import de.fourzerofournotfound.rateyourstuff.rays.models.Book;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.BookNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/episodes-rest")
public class BookController {

    @Autowired
    BookRepository repository;

    @GetMapping("/all")
    ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Book> getById(@PathVariable Long id) throws BookNotFoundException {
        return ResponseEntity.ok(this.repository.findById(id).orElseThrow(() -> new BookNotFoundException("No Book found for id " + id))); }

    @GetMapping()
    ResponseEntity<Book> findByTitle(@RequestParam(value = "title") String title) throws BookNotFoundException {
        return ResponseEntity.ok(this.repository.findByMediumName(title).orElseThrow(() -> new BookNotFoundException("No Episode with title " + title))); }

    @PostMapping(path="/add", consumes= "application/json", produces="application/json")
    ResponseEntity<Book> add(@RequestBody Book book) {
        return ResponseEntity.ok(this.repository.save(book));
    }

    @PutMapping(consumes="application/json", produces="application/json")
    ResponseEntity<Book> update(@RequestBody Book book) {
        return ResponseEntity.ok(this.repository.save(book));
    }

    @DeleteMapping("/{id}")
    void deleteBook (@PathVariable Long id) {
        this.repository.deleteById(id);
    }
}