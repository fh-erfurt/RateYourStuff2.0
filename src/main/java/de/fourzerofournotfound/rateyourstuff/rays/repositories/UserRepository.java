package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import de.fourzerofournotfound.rateyourstuff.rays.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This Interface will require methods for manipulating user storage
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByLastName(String lastName);
    public Optional<User> findByUserName(String username);
    public Optional<User> findByUserNameIgnoreCase(String username);
    public Optional<User> findUserByUserName(String userName);
    public Optional<User> findUserById(Long id);

}
