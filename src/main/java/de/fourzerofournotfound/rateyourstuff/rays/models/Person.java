package de.fourzerofournotfound.rateyourstuff.rays.models;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "Persons")

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
