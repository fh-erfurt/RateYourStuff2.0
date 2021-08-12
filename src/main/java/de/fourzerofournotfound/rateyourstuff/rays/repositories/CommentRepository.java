package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import de.fourzerofournotfound.rateyourstuff.rays.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository  extends JpaRepository<Comment, Long> {
    Page<Comment> findAll(Pageable pageable);
    Page<Comment> findAllByMediumId(Long mediumId, Pageable pageable);
    Page<Comment> findAllByUserId(Long userId, Pageable pageable);
    Long countAllByMediumId(Long mediumId );

    Page<Comment> findAllByCommentParentId(Long id, Pageable pageable);
}
