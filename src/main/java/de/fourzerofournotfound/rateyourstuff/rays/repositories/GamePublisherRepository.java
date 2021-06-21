package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import de.fourzerofournotfound.rateyourstuff.rays.models.GamePublisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GamePublisherRepository extends JpaRepository<GamePublisher, Long> {
    Optional<GamePublisher> findByGamePublisherTitle(String gamePublisherTitle);
}
