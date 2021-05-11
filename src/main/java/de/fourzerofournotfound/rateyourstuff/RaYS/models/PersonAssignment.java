package de.fourzerofournotfound.rateyourstuff.RaYS.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name="PersonAssignments")
public class PersonAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personAssignmentId;

    @Column(nullable = false)
    @ColumnDefault("CURRENT_TIMESTAMP()")
    private LocalDateTime createdAt;

    @Column(nullable = true)
    @ColumnDefault("NULL ON UPDATE CURRENT_TIMESTAMP()")
    private LocalDateTime updatedAt;

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
