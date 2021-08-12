package de.fourzerofournotfound.rateyourstuff.rays.models.media;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * <h1>Episode</h1>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
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
        if(super.getPicturePath() != null) {
            return IMAGE_PATH_PREFIX + super.getPicturePath();
        }
        return null;
    }

    public void setPicturePath(String picturePath) {
        super.setPicturePath(picturePath.replace(IMAGE_PATH_PREFIX, ""));
    }
}

