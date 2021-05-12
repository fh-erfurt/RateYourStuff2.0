package de.fourzerofournotfound.rateyourstuff.rays.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name = "Persons")

public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;

    @Column(nullable = false)
    @ColumnDefault("CURRENT_TIMESTAMP()")
    private LocalDateTime createdAt;

    @Column(nullable = true)
    @ColumnDefault("NULL ON UPDATE CURRENT_TIMESTAMP()")
    private LocalDateTime updatedAt;

    @Column (length=250)
    private String firstName;

    @Column (length=250)
    private String lastName;

    @Column
    private Date birthDate;

    @OneToMany (mappedBy = "person")
    private List<PersonAssignment> personAssignments;
}
