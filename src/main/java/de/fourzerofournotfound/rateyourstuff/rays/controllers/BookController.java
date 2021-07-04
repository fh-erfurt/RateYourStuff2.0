package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.BookDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Book;
import de.fourzerofournotfound.rateyourstuff.rays.models.Game;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.BookNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.BookRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.FileUploadService;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.isbn.ISBNCheckService;
import de.fourzerofournotfound.rateyourstuff.rays.services.isbn.InvalidISBNException;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/books")
public class BookController {

    @Autowired
    private BookRepository repository;

    @Autowired
    private FileUploadService fus;

    @Autowired
    private ISBNCheckService ics;

    @Autowired
    private PageableService pageableService;

    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    ResponseEntity<List<BookDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "") String orderBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Pageable pageable = pageableService.createPageable(orderBy, order, page, size);
        List<Book> books = this.repository.findAll(pageable).getContent();
        return ResponseEntity.ok(
                books.stream().map(bookService::convertToDto).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    ResponseEntity<BookDto> getById(@PathVariable Long id) throws BookNotFoundException {
        Optional<Book> book = this.repository.findById(id);
        if(book.isPresent()) {
            BookDto bookDto = bookService.convertToDto(book.get());
            return ResponseEntity.ok(bookDto);
        } else {
            throw new BookNotFoundException("No Book found for id " + id);
        }
    }

    @GetMapping()
    ResponseEntity<BookDto> findByTitle(@RequestParam(value = "title") String title) throws BookNotFoundException {
        Optional<Book> book = this.repository.findByMediumName(title);
        if(book.isPresent()) {
            BookDto bookDto = bookService.convertToDto(book.get());
            return ResponseEntity.ok(bookDto);
        } else {
            throw new BookNotFoundException("No Book with title " + title);
        }
    }

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