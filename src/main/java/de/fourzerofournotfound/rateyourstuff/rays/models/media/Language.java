package de.fourzerofournotfound.rateyourstuff.rays.models.media;


import de.fourzerofournotfound.rateyourstuff.rays.models.BaseModel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>This Model represents a language. The language described in which languages a medium is available in.
 * A medium can have multiple languages.</p>
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
@Table(name = "Languages")

public class Language extends BaseModel {

    @ManyToMany(mappedBy = "languages")
    Set<Medium> media = new HashSet<>();

    @Column(length = 200)
    private String language;
}
