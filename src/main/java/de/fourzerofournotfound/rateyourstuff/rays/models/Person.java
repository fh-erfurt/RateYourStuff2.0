package de.fourzerofournotfound.rateyourstuff.rays.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * <h1>Person</h1>
 * <p>This Model represents a Person. A Person can be assigned to different media.</p>
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
@Table(name = "Persons")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Builder
public class Person extends BaseModel {

    @Column (length=250)
    private String firstName;

    @Column (length=250)
    private String lastName;

    @Column
    private Date birthDate;

    @OneToMany (mappedBy = "person")
    private List<PersonAssignment> personAssignments;
}
