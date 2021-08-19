package de.fourzerofournotfound.rateyourstuff.rays.services;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.CommentController;
import de.fourzerofournotfound.rateyourstuff.rays.dtos.CommentDto;
import de.fourzerofournotfound.rateyourstuff.rays.errors.InvalidCommentException;
import de.fourzerofournotfound.rateyourstuff.rays.models.Comment;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Medium;
import de.fourzerofournotfound.rateyourstuff.rays.models.users.User;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.CommentRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.MediaRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.users.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>This Service provides methods to the {@link CommentController CommentController}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */

@Service("commentService")
public class CommentService {
    private final CommentRepository commentRepository;
    private final MediaRepository mediaRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentService(CommentRepository commentRepository,
                          MediaRepository mediaRepository,
                          UserRepository userRepository,
                          ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.mediaRepository = mediaRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Adds references for medium, user and parent-comment to the given comment
     * The parent-comment can also be null
     *
     * @param comment The comment that should be corrected
     * @return The comment with valid references to medium, user and comment
     * @throws InvalidCommentException If either mediumId or userId are invalid
     */
    public Comment addReferencesToComment(Comment comment) throws InvalidCommentException {

        Optional<Comment> parentComment = Optional.empty();
        if (comment.getParentMappingId() != null) {
            parentComment = commentRepository.findById(comment.getParentMappingId());
        }

        Optional<Medium> referencedMedium = mediaRepository.findById(comment.getMediumMappingId());
        Optional<User> referencedUser = userRepository.findById(comment.getUserMappingId());

        if (referencedMedium.isPresent() && referencedUser.isPresent()) {
            comment.setMedium(referencedMedium.get());
            comment.setUser(referencedUser.get());
            parentComment.ifPresent(comment::setCommentParent);
            return comment;
        }
        throw new InvalidCommentException("The given comment must have a valid mediumId and an valid userId");
    }

    /**
     * Converts a given comment to a commentDTO object to limit the data that gets sent to the client
     *
     * @param comment the comment that should be converted
     * @return the corresponding dtoObject
     */
    public CommentDto convertToDto(Comment comment) {
        return modelMapper.map(comment, CommentDto.class);
    }

}
