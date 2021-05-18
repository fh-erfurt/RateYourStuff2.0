package de.fourzerofournotfound.rateyourstuff.rays.models;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "Games")
public class Game extends Medium{

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long gameId;

    @Column(nullable = false)
    @ColumnDefault("CURRENT_TIMESTAMP()")
    private LocalDateTime createdAt;

    @Column
    @ColumnDefault("NULL ON UPDATE CURRENT_TIMESTAMP()")
    private LocalDateTime updatedAt;

    @Column
    private Float avaragePlaytime;

    @Column(nullable = false)
    private String publisher;

    /*@Column(nullable = false)
    private List<String> languages;

    @Column
    private List<String> subtitles;*/

    @Column(nullable = false)
    private Integer minNumberOfGamers;

    @Column
    private Integer maxNumberOfGamers;

   /* @Column
    private List<String> plattforms;*/

    @Column(nullable = false)
    private Integer ageRestriction;

    //@OneToOne
    //@JoinColumn(name = "mediumId", referencedColumnName = "mediumId")
    //private Medium medium;

}
