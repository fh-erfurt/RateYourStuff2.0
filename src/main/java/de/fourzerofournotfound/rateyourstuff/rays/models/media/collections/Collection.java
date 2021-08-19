package de.fourzerofournotfound.rateyourstuff.rays.models.media.collections;

import com.fasterxml.jackson.annotation.JsonInclude;
import de.fourzerofournotfound.rateyourstuff.rays.models.BaseModel;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Medium;
import de.fourzerofournotfound.rateyourstuff.rays.models.users.User;
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

    //used to pass only the id of the user from client to backend
    @Transient
    @JsonInclude
    private Long userMappingId;
}
