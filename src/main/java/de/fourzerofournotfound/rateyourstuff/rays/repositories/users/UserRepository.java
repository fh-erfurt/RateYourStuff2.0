package de.fourzerofournotfound.rateyourstuff.rays.repositories.users;

import de.fourzerofournotfound.rateyourstuff.rays.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This Interface will require methods for manipulating user storage
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLastName(String lastName);
    Optional<User> findByUserName(String username);
    Optional<User> findByUserNameIgnoreCase(String username);
    Optional<User> findUserByUserName(String userName);
    Optional<User> findUserById(Long id);

}
