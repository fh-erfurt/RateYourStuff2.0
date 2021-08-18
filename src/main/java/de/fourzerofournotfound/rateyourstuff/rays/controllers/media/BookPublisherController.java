package de.fourzerofournotfound.rateyourstuff.rays.controllers.media;


import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.BookPublisherDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.BookPublisher;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.BookPublisherRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.BookPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * BookPublisherController
 * <p>This Controller provides basic REST Interfaces to interact with BookPublisher entities from the database</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
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

    /**
     * This Method returns all BookPublishers from the database
     *
     * @param page    the current page (optional)
     * @param size    the number of items per page
     * @param orderBy the attributed that should be ordered
     * @param order   the order (asc, desc)
     * @return a list of BookDTOs
     */
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
}