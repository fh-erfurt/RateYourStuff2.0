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
@Table(name = "Series")
public class Serie {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serieId;

    @Column(nullable = false)
    @ColumnDefault("CURRENT_TIMESTAMP()")
    private LocalDateTime createdAt;

    @Column(nullable = true)
    @ColumnDefault("NULL ON UPDATE CURRENT_TIMESTAMP()")
    private LocalDateTime updatedAt;

    @Column(nullable = true)
    private Integer avarageLength;

    @Column(nullable = false)
    private String network;

//    @Column
//    private Person directors;
//
//    @Column(nullable = false)
//    private List<String> languages;
//
//   @Column
//    private List<String> plattforms;

    @Column(nullable = false)
    private String highestResolution;

    @Column(nullable = false)
    private Integer ageRestriction;

    @Column(nullable = false)
    private Boolean isCompleted;

    @OneToOne
    @JoinColumn(name = "mediumId", referencedColumnName = "mediumId")
    private Medium medium;

}
