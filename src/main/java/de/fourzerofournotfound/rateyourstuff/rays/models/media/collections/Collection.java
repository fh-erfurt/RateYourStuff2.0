package de.fourzerofournotfound.rateyourstuff.rays.models.media.collections;

import com.fasterxml.jackson.annotation.JsonInclude;
import de.fourzerofournotfound.rateyourstuff.rays.models.BaseModel;
import de.fourzerofournotfound.rateyourstuff.rays.models.users.User;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Medium;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * <p>This Model represents a Collection. A Collection is a set of different media (e. g. books, games, series)</p>
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
@Table(name = "Collections")
public class Collection extends BaseModel {

    @ManyToMany
    Set<Medium> media;
    @Column
    private String title;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;
    @Transient
    @JsonInclude
    private Long userMappingId;
}
