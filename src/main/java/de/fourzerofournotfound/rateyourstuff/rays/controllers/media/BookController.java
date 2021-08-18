package de.fourzerofournotfound.rateyourstuff.rays.controllers.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.BookDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.BookNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Book;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.BookRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.FileUploadService;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.DuplicateMediumException;
import de.fourzerofournotfound.rateyourstuff.rays.services.isbn.ISBNCheckService;
import de.fourzerofournotfound.rateyourstuff.rays.services.isbn.InvalidISBNException;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.BookPublisherService;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.BookService;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.GenreService;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Book Controller
 * <p>This Controller provides basic REST Interfaces to interact with Book entities from the database</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@RestController
@RequestMapping("/rest/books")
public class BookController {

    private final BookRepository bookRepository;
    private final FileUploadService fileUploadService;
    private final ISBNCheckService isbnCheckService;
    private final PageableService pageableService;
    private final BookService bookService;
    private final GenreService genreService;
    private final LanguageService languageService;
    private final BookPublisherService bookPublisherService;

    @Autowired
    public BookController(BookRepository bookRepository,
                          FileUploadService fileUploadService,
                          ISBNCheckService isbnCheckService,
                          PageableService pageableService,
                          BookService bookService,
                          GenreService genreService,
                          LanguageService languageService, BookPublisherService bookPublisherService) {
        this.bookRepository = bookRepository;
        this.fileUploadService = fileUploadService;
        this.isbnCheckService = isbnCheckService;
        this.pageableService = pageableService;
        this.bookService = bookService;
        this.genreService = genreService;
        this.languageService = languageService;
        this.bookPublisherService = bookPublisherService;
    }

    /**
     * This Method returns all books from the database
     *
     * @param page    the current page (optional)
     * @param size    the number of items per page
     * @param orderBy the attributed that should be ordered
     * @param order   the order (asc, desc)
     * @return a list of BookDTOs
     */
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

    /**
     * This Method returns a book by a given id
     *
     * @param id the id of the book that should be returned
     * @return the found book
     * @throws BookNotFoundException if there is no book with the given id
     */
    @GetMapping("/{id}")
    ResponseEntity<BookDto> getById(@PathVariable Long id) throws BookNotFoundException {
        Optional<Book> book = this.bookRepository.findById(id);
        if (book.isPresent()) {
            BookDto bookDto = bookService.convertToDto(book.get());
            return ResponseEntity.ok(bookDto);
        } else {
            throw new BookNotFoundException("No Book found for id " + id);
        }
    }

    /**
     * This Method is used to add a new book to the database.
     *
     * @param book the book that should be added
     * @return the saved book entity
     * @throws InvalidISBNException     if the given ISBN does not match the standard
     * @throws DuplicateMediumException if there is already a book with the given isbn
     */
    @PreAuthorize("hasAuthority('User')")
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    ResponseEntity<BookDto> add(@RequestBody Book book) throws InvalidISBNException, DuplicateMediumException {
        if (isbnCheckService.checkIfISBNisValid(book)) {
            if (bookService.isValidBook(book)) {
                this.bookRepository.save(book);
                book.setGenres(this.genreService.getGenresSet(book.getGenreStrings()));
                book.setLanguages(this.languageService.getLanguageSet(book.getLanguageStrings()));
                book.setBookPublisher(this.bookPublisherService.getPublisher(book.getPublisherString()));
                Book savedBook = this.bookRepository.save(book);
                return ResponseEntity.ok(bookService.convertToDto(savedBook));
            }
            throw new DuplicateMediumException("There is already a book with the ISBN" + book.getIsbn());
        } else {
            throw new InvalidISBNException("The ISBN " + book.getIsbn() + " is not valid");
        }
    }

    /**
     * This Method is used to update an existing Book.
     *
     * @param book the book that should be updated
     * @return the updated book
     * @throws InvalidISBNException     if the given ISBN does not match the standard
     * @throws DuplicateMediumException if there is already a book with the given isbn
     */
    @PreAuthorize("hasAuthority('User')")
    @PutMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<BookDto> update(@RequestBody Book book) throws InvalidISBNException, DuplicateMediumException {
        if (isbnCheckService.checkIfISBNisValid(book)) {
            if (bookService.isValidBook(book)) {
                book.setBookPublisher(this.bookPublisherService.getPublisher(book.getPublisherString()));
                this.bookRepository.save(book);
                book.setGenres(this.genreService.getGenresSet(book.getGenreStrings()));
                book.setLanguages(this.languageService.getLanguageSet(book.getLanguageStrings()));
                Book savedBook = this.bookRepository.save(book);
                return ResponseEntity.ok(bookService.convertToDto(savedBook));
            }
            throw new DuplicateMediumException("There is already a book with the ISBN" + book.getIsbn());
        } else {
            throw new InvalidISBNException("The ISBN " + book.getIsbn() + " is not valid");
        }
    }


    /**
     * This method is used to add a image to an existing book
     *
     * @param multipartFile the image file that should be uploaded
     * @param id            the id of the book that should be updated
     * @return the updated book entity
     * @throws IOException           if the file cannot be uploaded
     * @throws BookNotFoundException if there is no book with the given id
     */
    @PreAuthorize("hasAuthority('User')")
    @PostMapping("/images/{id}")
    ResponseEntity<BookDto> addImage(@RequestParam("image") MultipartFile multipartFile, @PathVariable Long id) throws IOException, BookNotFoundException {
        String fileName = StringUtils.cleanPath("poster." + fileUploadService.getFileExtension(multipartFile));
        Optional<Book> book = this.bookRepository.findById(id);
        //check if the given movie exists
        if (book.isPresent()) {
            book.get().setPicturePath(book.get().getId() + "/" + fileName);
            //define the target path
            String uploadDir = Book.IMAGE_PATH_PREFIX + id;
            //upload the file
            fileUploadService.saveFile(uploadDir, fileName, multipartFile);
            Book savedBook = this.bookRepository.save(book.get());
            return ResponseEntity.ok(bookService.convertToDto(savedBook));
        }
        throw new BookNotFoundException("There is no book with id " + id);
    }
}