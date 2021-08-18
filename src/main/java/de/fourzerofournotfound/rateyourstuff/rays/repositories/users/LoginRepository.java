package de.fourzerofournotfound.rateyourstuff.rays.repositories.users;

import de.fourzerofournotfound.rateyourstuff.rays.models.users.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This Interface will require methods for manipulating login storage
 */

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
    Optional<Login> findByEmail(String Email);
    Optional<Login> findByEmailIgnoreCase(String Email);

    Optional<Login> findLoginByEmailNotIgnoreCase(String email);
    Optional<Login> findLoginByEmailIgnoreCase(String email);
    Optional<Login> findLoginById(Long id);
}
