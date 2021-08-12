package de.fourzerofournotfound.rateyourstuff.rays.controllers.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.BookDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Book;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.BookNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.BookRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.FileUploadService;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.MediaService;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.isbn.ISBNCheckService;
import de.fourzerofournotfound.rateyourstuff.rays.services.isbn.InvalidISBNException;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/books")
public class BookController {

    private final BookRepository bookRepository;
    private final FileUploadService fileUploadService;
    private final ISBNCheckService isbnCheckService;
    private final PageableService pageableService;
    private final BookService bookService;
    private final MediaService mediaService;

    @Autowired
    public BookController(BookRepository bookRepository, FileUploadService fileUploadService, ISBNCheckService isbnCheckService, PageableService pageableService, BookService bookService, MediaService mediaService) {
        this.bookRepository = bookRepository;
        this.fileUploadService = fileUploadService;
        this.isbnCheckService = isbnCheckService;
        this.pageableService = pageableService;
        this.bookService = bookService;
        this.mediaService = mediaService;
    }

    @GetMapping("/all")
    ResponseEntity<List<BookDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "") String orderBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Pageable pageable = pageableService.createPageable(orderBy, order, page, size);
        List<Book> books = this.bookRepository.findAll(pageable).getContent();
        return ResponseEntity.ok(
                books.stream().map(bookService::convertToDto).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    ResponseEntity<BookDto> getById(@PathVariable Long id) throws BookNotFoundException {
        Optional<Book> book = this.bookRepository.findById(id);
        if(book.isPresent()) {
            BookDto bookDto = bookService.convertToDto(book.get());
            return ResponseEntity.ok(bookDto);
        } else {
            throw new BookNotFoundException("No Book found for id " + id);
        }
    }

    @GetMapping()
    ResponseEntity<BookDto> findByTitle(@RequestParam(value = "title") String title) throws BookNotFoundException {
        Optional<Book> book = this.bookRepository.findByMediumName(title);
        if(book.isPresent()) {
            BookDto bookDto = bookService.convertToDto(book.get());
            return ResponseEntity.ok(bookDto);
        } else {
            throw new BookNotFoundException("No Book with title " + title);
        }
    }

    @PostMapping(path="/add", consumes= "application/json", produces="application/json")
    ResponseEntity<Book> add(@RequestBody Book book) throws InvalidISBNException {
        if(isbnCheckService.checkIfISBNisValid(book) && mediaService.isValidBook(book)) {
            this.bookRepository.save(book);
            book.setGenres(this.mediaService.getGenresSet(book.getGenreStrings(), book));
            book.setLanguages(this.mediaService.getLanguageSet(book.getLanguageStrings(), book));
            book.setBookPublisher(this.bookService.getPublisher(book.getPublisherString(), book));
            return ResponseEntity.ok(this.bookRepository.save(book));
        } else {
            throw new InvalidISBNException("The ISBN " + book.getIsbn() + " is not valid");
        }
    }

    @PutMapping(consumes="application/json", produces="application/json")
    ResponseEntity<Book> update(@RequestBody Book book) throws InvalidISBNException {
        if(isbnCheckService.checkIfISBNisValid(book)) {
            return ResponseEntity.ok(this.bookRepository.save(book));
        } else {
            throw new InvalidISBNException("The ISBN " + book.getIsbn() + " is not valid");
        }
    }

    @DeleteMapping("/{id}")
    void deleteBook (@PathVariable Long id) {
        this.bookRepository.deleteById(id);
    }

    @PostMapping("/images/{id}")
    ResponseEntity<Book> addImage(@RequestParam("image") MultipartFile multipartFile, @PathVariable Long id) throws IOException {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            Optional<Book> book = this.bookRepository.findById(id);
            //check if the given movie exists
            if(book.isPresent()) {
                book.get().setPicturePath(book.get().getId() + "/" + fileName);
                //define the target path
                String uploadDir = Book.IMAGE_PATH_PREFIX + id;
                //upload the file
                fileUploadService.saveFile(uploadDir, fileName, multipartFile);
                return ResponseEntity.ok(this.bookRepository.save(book.get()));
            }
        return ResponseEntity.badRequest().build();
    }
}