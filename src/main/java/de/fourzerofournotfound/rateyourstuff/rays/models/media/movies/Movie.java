package de.fourzerofournotfound.rateyourstuff.rays.models.media.movies;

import com.fasterxml.jackson.annotation.JsonInclude;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Medium;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Network;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * <p>This model represents a single movie</p>
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
@Table(name = "Movies")
public class Movie extends Medium {

    public final static String IMAGE_PATH_PREFIX = "images/media/movies/";
    @JsonInclude

    //used to pass only the network title from client to backend
    @Transient
    String networkTitle;

    @Column
    private Integer length;

    @Column
    private Integer ageRestriction;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "networkId", referencedColumnName = "id")
    private Network network;

    @Builder
    public Movie(String mediumName, String shortDescription, LocalDate releaseDate, Integer length, Integer ageRestriction) {
        this.setMediumName(mediumName);
        this.setShortDescription(shortDescription);
        this.setReleaseDate(releaseDate);
        this.length = length;
        this.ageRestriction = ageRestriction;
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
