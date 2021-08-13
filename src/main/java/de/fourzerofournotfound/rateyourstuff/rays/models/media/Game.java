package de.fourzerofournotfound.rateyourstuff.rays.models.media;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * <h1>Game</h1>
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
@Table(name = "Games")
public class Game extends Medium{

    public final static String IMAGE_PATH_PREFIX = "images/media/games/";

    @Column
    private Float averagePlaytime;

    @Column(nullable = false)
    private Integer minNumberOfGamers;

    @Column
    private Integer maxNumberOfGamers;

    @ManyToMany
    Set<Platform> platforms;

    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name = "gamePublisherId", referencedColumnName = "id")
    private GamePublisher gamePublisher;


    @Column(nullable = false)
    private Integer ageRestriction;

    @JsonInclude
    @Transient
    String publisherTitle;

    @JsonInclude
    @Transient
    List<String> platformStrings;

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
