package de.fourzerofournotfound.rateyourstuff.rays.repositories.users;

import de.fourzerofournotfound.rateyourstuff.rays.models.users.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <p>This Interface can be used to find Login entities in the database.</p>
 * <p>It also provides all functions of the {@link JpaRepository JpaRepository}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
    Optional<Login> findByEmail(String Email);

    Optional<Login> findLoginByEmailNotIgnoreCase(String email);
    Optional<Login> findLoginByEmailIgnoreCase(String email);
    Optional<Login> findLoginById(Long id);
}
