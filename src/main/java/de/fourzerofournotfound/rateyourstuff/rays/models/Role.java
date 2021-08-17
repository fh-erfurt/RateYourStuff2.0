package de.fourzerofournotfound.rateyourstuff.rays.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

/**
 * Role
 * <p>This Model represents a role. A role defines the "group" of an user. e. g. administrator, moderator, user,...</p>
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "Roles")
public class Role extends BaseModel {

    @Column(nullable = false)
    private String roleName;

}
