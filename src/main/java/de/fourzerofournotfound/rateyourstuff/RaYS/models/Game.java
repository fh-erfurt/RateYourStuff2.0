package de.fourzerofournotfound.rateyourstuff.RaYS.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;


public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;

    @Column(nullable = false)
    @ColumnDefault("CURRENT_TIMESTAMP()")
    private LocalDateTime createdAt;

    @Column(nullable = true)
    @ColumnDefault("NULL ON UPDATE CURRENT_TIMESTAMP()")
    private LocalDateTime updatedAt;

    @Column(nullable = true)
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

    @OneToOne
    @JoinColumn(name = "mediumId", referencedColumnName = "mediumId")
    private Medium medium;

}
