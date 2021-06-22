package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import de.fourzerofournotfound.rateyourstuff.rays.models.PersonAssignment;
import de.fourzerofournotfound.rateyourstuff.rays.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonAssigmentRepository extends JpaRepository<PersonAssignment,Long> {
}
