package de.fourzerofournotfound.rateyourstuff.rays.models.media.series;

import com.fasterxml.jackson.annotation.JsonInclude;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Medium;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Network;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.series.Season;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Series
 *
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
@Table(name = "Series")
public class Series extends Medium {

    public final static String IMAGE_PATH_PREFIX = "images/media/series/";
    @JsonInclude
    @Transient
    String networkTitle;
    @Column
    private Integer averageLength;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "networkId", referencedColumnName = "id")
    private Network network;
    @Column(nullable = false)
    private Integer ageRestriction;
    @Column(nullable = false)
    private Boolean isCompleted;
    @OneToMany(mappedBy = "medium", cascade = CascadeType.ALL)
    private Set<Season> seasons = new HashSet<>();

    @Builder
    public Series(String mediumName, String shortDescription, LocalDate releaseDate, Integer averageLength, Integer ageRestriction, Boolean isCompleted) {
        this.setMediumName(mediumName);
        this.setShortDescription(shortDescription);
        this.setReleaseDate(releaseDate);
        this.averageLength = averageLength;
        this.ageRestriction = ageRestriction;
        this.isCompleted = isCompleted;
    }

    public String getPicturePath() {
        if (super.getPicturePath() != null) {
            return IMAGE_PATH_PREFIX + super.getPicturePath();
        }
        return null;
    }

    public void setPicturePath(String picturePath) {
        super.setPicturePath(picturePath.replace(IMAGE_PATH_PREFIX, ""));
    }
}
