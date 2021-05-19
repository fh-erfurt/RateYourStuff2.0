package de.fourzerofournotfound.rateyourstuff.rays.models;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
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
    private Float averagePlaytime;

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

    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name = "plattformId", referencedColumnName = "platformId")
    private Platform platform;

    @Column(nullable = false)
    private Integer ageRestriction;

    //@OneToOne
    //@JoinColumn(name = "mediumId", referencedColumnName = "mediumId")
    //private Medium medium;

    @Builder
    public Game(String mediumName,
                String shortDescription,
                LocalDate releaseDate,
                float averagePlaytime ,
                String publisher,
                int minNumberOfGamers,
                int maxNumberOfGamers,
                int ageRestriction)
    {
       this.setMediumName(mediumName);
       this.setShortDescription(shortDescription);
       this.setReleaseDate(releaseDate);
       this.averagePlaytime = averagePlaytime;
       this.publisher = publisher;
       this.minNumberOfGamers = minNumberOfGamers;
       this.maxNumberOfGamers = maxNumberOfGamers;
       this.ageRestriction = ageRestriction;
    }

}
