package de.fourzerofournotfound.rateyourstuff.rays.repositories;
import de.fourzerofournotfound.rateyourstuff.rays.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByRoleNameIgnoreCase(String RoleName);
}
