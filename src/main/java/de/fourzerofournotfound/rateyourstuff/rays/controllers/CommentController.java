package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.CommentDto;
import de.fourzerofournotfound.rateyourstuff.rays.errors.CommentNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.errors.InvalidCommentException;
import de.fourzerofournotfound.rateyourstuff.rays.models.Comment;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.CommentRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.CommentService;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * <p>This Controller provides basic REST Interfaces to interact with Comment entities from the database</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@RestController
@RequestMapping("/rest/comments")
public class CommentController {

    private final CommentRepository commentRepository;
    private final PageableService pageableService;
    private final CommentService commentService;
    private final Logger logger = Logger.getLogger(this.getClass().getName());


    @Autowired
    public CommentController(CommentRepository commentRepository,
                             PageableService pageableService,
                             CommentService commentService) {
        this.commentRepository = commentRepository;
        this.pageableService = pageableService;
        this.commentService = commentService;
    }

    /**
     * This Method returns all comments from the database
     *
     * @param page    the current page (optional)
     * @param size    the number of items per page
     * @param orderBy the attributed that should be ordered
     * @param order   the order (asc, desc)
     * @return a list of comment dtos
     */
    @GetMapping("/all")
    ResponseEntity<List<CommentDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "createdAt") String orderBy,
            @RequestParam(defaultValue = "desc") String order
    ) {
        Pageable pageable = pageableService.createPageable(orderBy, order, page, size);
        List<Comment> comments = commentRepository.findAll(pageable).getContent();
        return ResponseEntity.ok(
                comments.stream()
                        .map(commentService::convertToDto)
                        .collect(Collectors.toList())
        );
    }

    /**
     * This Method returns all comments for a specific medium from the database
     *
     * @param page     the current page (optional)
     * @param size     the number of items per page
     * @param orderBy  the attributed that should be ordered
     * @param order    the order (asc, desc)
     * @param mediumId the id of the medium for which comments should be searched
     * @return a list of comment dtos
     */
    @GetMapping("/all/medium/{mediumId}")
    ResponseEntity<List<CommentDto>> getAllByMediumId(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "createdAt") String orderBy,
            @RequestParam(defaultValue = "desc") String order,
            @PathVariable Long mediumId
    ) {
        Pageable pageable = pageableService.createPageable(orderBy, order, page, size);
        List<Comment> comments = commentRepository.findAllByCommentParentIsNullAndMediumId(mediumId, pageable).getContent();
        return ResponseEntity.ok(
                comments.stream()
                        .map(commentService::convertToDto)
                        .collect(Collectors.toList())
        );
    }

    /**
     * This Method returns all comments for a specific user from the database
     *
     * @param page    the current page (optional)
     * @param size    the number of items per page
     * @param orderBy the attributed that should be ordered
     * @param order   the order (asc, desc)
     * @param userId  the id of the user for which comments should be searched
     * @return a list of comment dtos
     */
    @GetMapping("/all/user/{userId}")
    ResponseEntity<List<CommentDto>> getAllByUserId(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "createdAt") String orderBy,
            @RequestParam(defaultValue = "desc") String order,
            @PathVariable Long userId
    ) {
        Pageable pageable = pageableService.createPageable(orderBy, order, page, size);
        List<Comment> comments = commentRepository.findAllByUserId(userId, pageable).getContent();
        return ResponseEntity.ok(
                comments.stream()
                        .map(commentService::convertToDto)
                        .collect(Collectors.toList())
        );
    }

    /**
     * This Method returns all answers to a certain comment from the database
     *
     * @param page     the current page (optional)
     * @param size     the number of items per page
     * @param orderBy  the attributed that should be ordered
     * @param order    the order (asc, desc)
     * @param parentId the id of the comment for which comments(answers) should be searched
     * @return a list of comment dtos
     */
    @GetMapping("/all/subcomments/{parentId}")
    ResponseEntity<List<CommentDto>> getAllByParentCommentId(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "createdAt") String orderBy,
            @RequestParam(defaultValue = "asc") String order,
            @PathVariable Long parentId
    ) {
        Pageable pageable = pageableService.createPageable(orderBy, order, page, size);
        List<Comment> comments = commentRepository.findAllByCommentParentId(parentId, pageable).getContent();
        return ResponseEntity.ok(
                comments.stream()
                        .map(commentService::convertToDto)
                        .collect(Collectors.toList())
        );
    }

    /**
     * This method is used to get a specific comment from the database
     *
     * @param id the id of the comment that should be searched
     * @return a CommentDto of the found comment
     * @throws CommentNotFoundException if there is no comment that matches the id
     */
    @GetMapping("/{id}")
    ResponseEntity<CommentDto> getById(@PathVariable Long id) throws CommentNotFoundException {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            return ResponseEntity.ok(commentService.convertToDto(comment.get()));
        } else {
            throw new CommentNotFoundException("No " + Comment.class.getSimpleName() + " found for id " + id);
        }
    }

    /**
     * This method is used to add a new comment to the database
     *
     * @param comment the comment that should be added to the database
     * @return a dto of the newly added comment
     * @throws InvalidCommentException if the comment contains invalid values
     */
    @PreAuthorize("hasAuthority('User')")
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    ResponseEntity<CommentDto> add(@RequestBody Comment comment) throws InvalidCommentException {
        comment = commentService.addReferencesToComment(comment);
        Comment savedComment = this.commentRepository.save(comment);
        logger.info("Added " + Comment.class.getSimpleName() + " with id " + savedComment.getId());
        return ResponseEntity.ok(commentService.convertToDto(savedComment));
    }

    /**
     * This method is used to update an existing comment from the database
     *
     * @param comment the comment that should be updated
     * @return a dto of the updated comment
     * @throws CommentNotFoundException if the comment contains invalid values
     */
    @PreAuthorize("hasAuthority('User')")
    @PutMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<CommentDto> update(@RequestBody Comment comment) throws CommentNotFoundException {
        Optional<Comment> foundComment = commentRepository.findById(comment.getId());
        if (foundComment.isPresent()) {
            foundComment.get().setTextOfComment(comment.getTextOfComment());
            Comment savedComment = this.commentRepository.save(foundComment.get());
            logger.info("Updated " + Comment.class.getSimpleName() + " with id " + savedComment.getId());
            return ResponseEntity.ok(commentService.convertToDto(savedComment));
        }
        throw new CommentNotFoundException("There is no " + Comment.class.getSimpleName() + " with id " + comment.getId());
    }

    /**
     * This method is used to delete a comment and all of its subcomments (answers) from the database
     *
     * @param id the id of the comment that should be deleted
     * @return http status 200
     */
    @PreAuthorize("hasAuthority('User')")
    @DeleteMapping("/{id}")
    HttpStatus deleteComment(@PathVariable Long id) {
        commentRepository.deleteById(id);
        logger.info("Deleted " + Comment.class.getSimpleName() + " with id " + id);
        return HttpStatus.OK;
    }

    /**
     * This method is used to count how many comments are saved for a specific medium
     *
     * @param mediumId the medium id that should be searched
     * @return the number of comments for the given medium id
     */
    @GetMapping("/count/{mediumId}")
    ResponseEntity<Long> countComments(@PathVariable Long mediumId) {
        return ResponseEntity.ok(this.commentRepository.countAllByMediumId(mediumId));
    }

}
