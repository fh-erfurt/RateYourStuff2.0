package de.fourzerofournotfound.rateyourstuff.rays.models.media;

import com.fasterxml.jackson.annotation.JsonBackReference;
import de.fourzerofournotfound.rateyourstuff.rays.models.BaseModel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>This Model represents a genre. Each medium can have multiple genres</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "Genres")
public class Genre extends BaseModel {

    @ManyToMany(mappedBy = "genres")
    Set<Medium> media = new HashSet<>();

    @Column(nullable = false, length = 45)
    private String genreName;
}
