package de.fourzerofournotfound.rateyourstuff.rays.models;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;


@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "Games")
public class Game extends Medium{

    @Column
    private Float averagePlaytime;

    @Column(nullable = false)
    private Integer minNumberOfGamers;

    @Column
    private Integer maxNumberOfGamers;

    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name = "platformId", referencedColumnName = "id")
    private Platform platform;

    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name = "gamePublisherId", referencedColumnName = "id")
    private GamePublisher gamePublisher;

    /*@ManyToMany
    Set<GamePublisher> gamePublisher;*/

    @Column(nullable = false)
    private Integer ageRestriction;

    @Builder
    public Game(String mediumName,
                String shortDescription,
                LocalDate releaseDate,
                float averagePlaytime ,
                int minNumberOfGamers,
                int maxNumberOfGamers,
                int ageRestriction)
    {
       this.setMediumName(mediumName);
       this.setShortDescription(shortDescription);
       this.setReleaseDate(releaseDate);
       this.averagePlaytime = averagePlaytime;
       this.minNumberOfGamers = minNumberOfGamers;
       this.maxNumberOfGamers = maxNumberOfGamers;
       this.ageRestriction = ageRestriction;
    }

}
