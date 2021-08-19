package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import de.fourzerofournotfound.rateyourstuff.rays.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>This Interface can be used to find Comment entities in the database.</p>
 * <p>It also provides all functions of the {@link JpaRepository JpaRepository}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAll(Pageable pageable);

    Page<Comment> findAllByUserId(Long userId, Pageable pageable);

    Page<Comment> findAllByCommentParentIsNullAndMediumId(Long mediumId, Pageable pageable);

    Long countAllByMediumId(Long mediumId);

    Page<Comment> findAllByCommentParentId(Long id, Pageable pageable);
}
