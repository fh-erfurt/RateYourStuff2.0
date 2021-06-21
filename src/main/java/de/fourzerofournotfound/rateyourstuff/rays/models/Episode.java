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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "Episodes")
public class Episode extends Medium {

    public final static String IMAGE_PATH_PREFIX = "images/media/episodes/";

    @Column
    private Integer episodeNumber;

    @Column
    private Integer length;

    @ManyToOne
    @JoinColumn(name = "seasonId", referencedColumnName = "id")
    private Season season;

    @Builder
    public Episode(String mediumName, String shortDescription, LocalDate releaseDate, Integer episodeNumber, Integer length) {
        this.setMediumName(mediumName);
        this.setShortDescription(shortDescription);
        this.setReleaseDate(releaseDate);
        this.episodeNumber = episodeNumber;
        this.length = length;
    }

    public String getPicturePath() {
        return IMAGE_PATH_PREFIX + super.getPicturePath();
    }

    public void setPicturePath(String picturePath) {
        super.setPicturePath(picturePath.replace(IMAGE_PATH_PREFIX, ""));
    }
}

