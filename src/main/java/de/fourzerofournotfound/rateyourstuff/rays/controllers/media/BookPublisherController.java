package de.fourzerofournotfound.rateyourstuff.rays.controllers.media;


import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.BookPublisherDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.BookPublisher;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.media.BookPublisherNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.BookPublisherRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.BookPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/bookPublishers")
public class BookPublisherController {

    final BookPublisherRepository bookPublisherRepository;
    final BookPublisherService bookPublisherService;
    final PageableService pageableService;

    @Autowired
    public BookPublisherController(BookPublisherRepository bookPublisherRepository, BookPublisherService bookPublisherService, PageableService pageableService) {
        this.bookPublisherRepository = bookPublisherRepository;
        this.bookPublisherService = bookPublisherService;
        this.pageableService = pageableService;
    }

    @GetMapping("/all")
    ResponseEntity<List<BookPublisherDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "") String orderBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Pageable pageable = pageableService.createPageable(orderBy, order, page, size);
        List<BookPublisher> publishers = this.bookPublisherRepository.findAll(pageable).getContent();

        return ResponseEntity.ok(publishers.stream()
                .map(bookPublisherService::convertToDto)
                .collect(Collectors.toList()));
    }

    @GetMapping()
    ResponseEntity<BookPublisher> findByTitle(@RequestParam(value = "title") String title) throws BookPublisherNotFoundException {
        return ResponseEntity.ok(this.bookPublisherRepository.findByBookPublisherTitle(title).orElseThrow(() -> new BookPublisherNotFoundException("No Publisher with title " + title)));
    }
}