package de.fourzerofournotfound.rateyourstuff.rays.models.media;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import de.fourzerofournotfound.rateyourstuff.rays.models.BaseModel;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * <h1>Language</h1>
 * <p>This Model represents a language. The language described in which languages a medium is available in.
 * A medium can have multiple languages.</p>
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "Languages")

public class Language extends BaseModel {

    @Column(length = 200)
    private String language;

    //@JsonManagedReference(value="media-languages")
    @ManyToMany()
    Set<Medium> media;
}
