package de.fourzerofournotfound.rateyourstuff.rays.repositories;
import de.fourzerofournotfound.rateyourstuff.rays.models.LoginRole;
import de.fourzerofournotfound.rateyourstuff.rays.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByRoleName(String RoleName);
}
