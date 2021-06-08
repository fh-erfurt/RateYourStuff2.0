package de.fourzerofournotfound.rateyourstuff.rays.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "mediumId")
@Table(name = "Episodes")
public class Episode extends Medium {

    @Column
    private Integer episodeNumber;

    @Column
    private Integer length;

    @ManyToOne
    @JoinColumn(name = "seasonId", referencedColumnName = "seasonId")
    private Season season;

    @Builder
    public Episode(String mediumName, String shortDescription, LocalDate releaseDate, Integer episodeNumber, Integer length) {
        this.setMediumName(mediumName);
        this.setShortDescription(shortDescription);
        this.setReleaseDate(releaseDate);
        this.episodeNumber = episodeNumber;
        this.length = length;
    }
}

