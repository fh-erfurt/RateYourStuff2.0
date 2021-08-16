package de.fourzerofournotfound.rateyourstuff.rays.models.media;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import de.fourzerofournotfound.rateyourstuff.rays.models.BaseModel;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Genre
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Genre extends BaseModel {

    @Column(nullable = false, length = 45)
    private String genreName;

    @JsonBackReference(value="media-genres")
    @ManyToMany(mappedBy="genres")
    Set<Medium> media = new HashSet<>();
}
