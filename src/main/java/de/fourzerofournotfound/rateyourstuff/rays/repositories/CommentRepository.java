package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import de.fourzerofournotfound.rateyourstuff.rays.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository  extends JpaRepository<Comment, Long> {
}
