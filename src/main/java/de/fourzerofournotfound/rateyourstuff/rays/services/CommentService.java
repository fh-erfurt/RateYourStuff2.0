package de.fourzerofournotfound.rateyourstuff.rays.services;

import de.fourzerofournotfound.rateyourstuff.rays.models.Comment;
import de.fourzerofournotfound.rateyourstuff.rays.models.Medium;
import de.fourzerofournotfound.rateyourstuff.rays.models.User;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.CommentRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.MediaRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.UserRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.InvalidCommentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("cs")
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Adds references for medium, user and parent-comment to the given comment
     * The parent-comment can also be null
     * @param comment   The comment that should be corrected
     * @return          The comment with valid references to medium, user and comment
     * @throws InvalidCommentException  If either mediumId or userId are invalid
     */
    public Comment addReferencesToComment(Comment comment) throws InvalidCommentException {
        Optional<Comment> parentComment = null;
        if(comment.getParentMappingId() != null) {
            parentComment = commentRepository.findById(comment.getParentMappingId());
        }

        Optional<Medium> referencedMedium = mediaRepository.findById(comment.getMediumMappingId());
        Optional<User> referencedUser = userRepository.findById(comment.getMediumMappingId());

        if(referencedMedium.isPresent() && referencedUser.isPresent()) {
            comment.setMedium(referencedMedium.get());
            comment.setUser(referencedUser.get());
            if(parentComment.isPresent()) {
                comment.setCommentParent(parentComment.get());
            }
            return comment;
        }
        throw new InvalidCommentException("The given comment must have a valid mediumId and an valid userId");
    }


}
