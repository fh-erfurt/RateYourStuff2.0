package de.fourzerofournotfound.rateyourstuff.rays.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

/**
 * <h1>Person Assignment</h1>
 * <p>This Model represents a Person Assignment.
 * This Model is used to assign multiple persons to a medium.
 * Each person can also have multiple roles</p>
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
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name="PersonAssignments")
public class PersonAssignment extends BaseModel {

    @Column
    String role;

    @ManyToOne
    @JoinColumn(name = "personId", referencedColumnName = "id")
    private Person person;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "mediumId", referencedColumnName = "id")
    private Medium medium;


}
