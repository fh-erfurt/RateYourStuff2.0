package de.fourzerofournotfound.rateyourstuff.rays.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * <h1>Genre</h1>
 * <p>This Model represents a genre. Each medium can have multiple genres</p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "Genres")
public class Genre extends BaseModel {

    @Column(nullable = false, length = 45)
    private String genreName;

    @JsonBackReference
    @ManyToMany
    Set<Medium> media;
}
