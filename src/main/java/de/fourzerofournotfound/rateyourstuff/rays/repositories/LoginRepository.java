package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import de.fourzerofournotfound.rateyourstuff.rays.models.Login;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.OptionalInt;

/**
 * This Interface will require methods for manipulating login storage
 */

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
    public Optional<Login> findByEmail(String Email);
    public Optional<Login> findByEmailIgnoreCase(String Email);

    public Optional<Login> findLoginByEmailNotIgnoreCase(String email);
    public Optional<Login> findLoginByEmailIgnoreCase(String email);
    public Optional<Login> findLoginById(Long id);
}
