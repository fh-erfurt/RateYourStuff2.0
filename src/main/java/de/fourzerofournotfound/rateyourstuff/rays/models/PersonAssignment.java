package de.fourzerofournotfound.rateyourstuff.rays.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

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
    private Boolean isActor;

    @Column
    private Boolean isDirector;

    @Column
    private Boolean isProducer;

    @Column
    private Boolean isAuthor;

    @ManyToOne
    @JoinColumn(name = "personId", referencedColumnName = "id")
    private Person person;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "mediumId", referencedColumnName = "id")
    private Medium medium;


}
