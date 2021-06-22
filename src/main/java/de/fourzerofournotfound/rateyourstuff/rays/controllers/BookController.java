package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.models.Book;
import de.fourzerofournotfound.rateyourstuff.rays.models.Game;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.BookNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.BookRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.FileUploadService;
import de.fourzerofournotfound.rateyourstuff.rays.services.isbn.ISBNCheckService;
import de.fourzerofournotfound.rateyourstuff.rays.services.isbn.InvalidISBNException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books-rest")
public class BookController {

    @Autowired
    BookRepository repository;

    @Autowired
    FileUploadService fus;

    @Autowired
    ISBNCheckService ics;

    @GetMapping("/all")
    ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Book> getById(@PathVariable Long id) throws BookNotFoundException {
        return ResponseEntity.ok(this.repository.findById(id).orElseThrow(() -> new BookNotFoundException("No Book found for id " + id))); }

    @GetMapping()
    ResponseEntity<Book> findByTitle(@RequestParam(value = "title") String title) throws BookNotFoundException {
        return ResponseEntity.ok(this.repository.findByMediumName(title).orElseThrow(() -> new BookNotFoundException("No Book with title " + title))); }

    @PostMapping(path="/add", consumes= "application/json", produces="application/json")
    ResponseEntity<Book> add(@RequestBody Book book) throws InvalidISBNException {
        if(ics.checkIfISBNisValid(book)) {
            return ResponseEntity.ok(this.repository.save(book));
        } else {
            throw new InvalidISBNException("The ISBN " + book.getIsbn() + " is not valid");
        }
    }

    @PutMapping(consumes="application/json", produces="application/json")
    ResponseEntity<Book> update(@RequestBody Book book) throws InvalidISBNException {
        if(ics.checkIfISBNisValid(book)) {
            return ResponseEntity.ok(this.repository.save(book));
        } else {
            throw new InvalidISBNException("The ISBN " + book.getIsbn() + " is not valid");
        }
    }

    @DeleteMapping("/{id}")
    void deleteBook (@PathVariable Long id) {
        this.repository.deleteById(id);
    }

    @PutMapping("/images/{id}")
    ResponseEntity<Book> addImage(@RequestParam("image") MultipartFile multipartFile, @PathVariable Long id) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Optional<Book> book = this.repository.findById(id);
        //check if the given movie exists
        if(book.isPresent()) {
            book.get().setPicturePath(book.get().getId() + "/" + fileName);
            //define the target path
            String uploadDir = Book.IMAGE_PATH_PREFIX + id.toString();
            //upload the file
            fus.saveFile(uploadDir, fileName, multipartFile);
            return ResponseEntity.ok(this.repository.save(book.get()));
        }
        return ResponseEntity.badRequest().build();
    }
}