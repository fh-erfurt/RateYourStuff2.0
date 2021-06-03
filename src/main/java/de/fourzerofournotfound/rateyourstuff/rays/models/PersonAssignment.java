package de.fourzerofournotfound.rateyourstuff.rays.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name="PersonAssignments")
public class PersonAssignment extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personAssignmentId;

    @Column
    private Boolean isActor;

    @Column
    private Boolean isDirector;

    @Column
    private Boolean isProducer;

    @Column
    private Boolean isAuthor;

    @ManyToOne
    @JoinColumn(name = "personId", referencedColumnName = "personId")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "mediumId", referencedColumnName = "mediumId")
    private Medium medium;


}
