package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.CommentDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Comment;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.CommentNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.CommentRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.CommentService;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.InvalidCommentException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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

    @GetMapping("/all")
    ResponseEntity<List<CommentDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "createdAt") String orderBy,
            @RequestParam(defaultValue = "desc") String order
    ) {
        Pageable pageable = pageableService.createPageable(orderBy, order, page, size);
        List<Comment> comments = commentRepository.findAllByCommentParentIsNull(pageable).getContent();
        return ResponseEntity.ok(
                comments.stream()
                .map(commentService::convertToDto)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/all/medium/{mediumId}")
    ResponseEntity<List<CommentDto>> getAllByMediumId(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "createdAt") String orderBy,
            @RequestParam(defaultValue = "desc") String order,
            @PathVariable Long mediumId
    ) {
        Pageable pageable = pageableService.createPageable(orderBy, order, page, size);
        List<Comment> comments = commentRepository.findAllByMediumId(mediumId, pageable).getContent();
        return ResponseEntity.ok(
                comments.stream()
                        .map(commentService::convertToDto)
                        .collect(Collectors.toList())
        );
    }

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

    @GetMapping("/{id}")
    ResponseEntity<CommentDto> getById (@PathVariable Long id) throws CommentNotFoundException {
        Optional<Comment> comment = commentRepository.findById(id);
        if(comment.isPresent()) {
            return ResponseEntity.ok(commentService.convertToDto(comment.get()));
        } else {
            throw new CommentNotFoundException("No "+Comment.class.getSimpleName()+" found for id " + id);
        }
    }

    @PreAuthorize("hasAuthority('User')")
    @PostMapping(path="/add", consumes= "application/json", produces="application/json")
    ResponseEntity<CommentDto> add(@RequestBody Comment comment) throws InvalidCommentException {
        comment = commentService.addReferencesToComment(comment);
        Comment savedComment = this.commentRepository.save(comment);
        logger.info("Added " + Comment.class.getSimpleName() + " with id " + savedComment.getId());
        return ResponseEntity.ok(commentService.convertToDto(savedComment));
    }

    @PreAuthorize("hasAuthority('User')")
    @PutMapping(consumes="application/json", produces="application/json")
    ResponseEntity<Comment> update(@RequestBody Comment comment) throws CommentNotFoundException {
        Optional<Comment> foundComment = commentRepository.findById(comment.getId());
        if(foundComment.isPresent()) {
            foundComment.get().setTextOfComment(comment.getTextOfComment());
            Comment savedComment = this.commentRepository.save(foundComment.get());
            logger.info("Updated " +Comment.class.getSimpleName()+ " with id " + savedComment.getId());
            return ResponseEntity.ok(savedComment);
        }
        throw new CommentNotFoundException("There is no "+Comment.class.getSimpleName()+" with id " + comment.getId());
    }

    @PreAuthorize("hasAuthority('User')")
    @DeleteMapping("/{id}")
    void deleteSeason (@PathVariable Long id) {
        this.commentRepository.deleteById(id);
    }

    @GetMapping("/count/{mediumId}")
    ResponseEntity<Long> countComments (@PathVariable Long mediumId){
        return ResponseEntity.ok(this.commentRepository.countAllByMediumId(mediumId));
    }

}
