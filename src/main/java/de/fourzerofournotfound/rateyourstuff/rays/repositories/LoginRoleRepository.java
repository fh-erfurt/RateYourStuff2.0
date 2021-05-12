package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import de.fourzerofournotfound.rateyourstuff.rays.models.LoginRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRoleRepository extends JpaRepository<LoginRole, Long> {

}