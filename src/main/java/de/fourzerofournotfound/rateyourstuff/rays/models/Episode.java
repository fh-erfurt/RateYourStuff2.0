package de.fourzerofournotfound.rateyourstuff.rays.models;

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
@Table(name = "Episodes")
public class Episode extends Medium {

    @Column(nullable = false)
    @ColumnDefault("CURRENT_TIMESTAMP()")
    private LocalDateTime createdAt;

    @Column
    @ColumnDefault("NULL ON UPDATE CURRENT_TIMESTAMP()")
    private LocalDateTime updatedAt;

    @Column
    private Integer episodeNumber;

    @Column
    private Integer length;

    @Builder
    @ManyToOne
    @JoinColumn(name = "seasonId", referencedColumnName = "seasonId")
    private Season season;

    public Episode(String mediumName, String shortDescription, LocalDate releaseDate, Integer episodeNumber, Integer length) {
        this.setMediumName(mediumName);
        this.setShortDescription(shortDescription);
        this.setReleaseDate(releaseDate);
        this.episodeNumber = episodeNumber;
        this.length = length;
    }
}

