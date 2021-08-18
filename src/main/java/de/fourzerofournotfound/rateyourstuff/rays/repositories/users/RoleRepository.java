package de.fourzerofournotfound.rateyourstuff.rays.repositories.users;
import de.fourzerofournotfound.rateyourstuff.rays.models.users.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByRoleNameIgnoreCase(String RoleName);
    List<Role> findAll();
}
