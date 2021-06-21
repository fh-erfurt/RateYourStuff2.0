package de.fourzerofournotfound.rateyourstuff.rays.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "Movies")
public class Movie extends Medium {

    public final static String IMAGE_PATH_PREFIX = "images/media/movies/";

    @Column
    private Integer length;

    @Column
    private Integer ageRestriction;

    @ManyToOne (cascade = CascadeType.PERSIST)
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
        return IMAGE_PATH_PREFIX + super.getPicturePath();
    }

    public void setPicturePath(String picturePath) {
        super.setPicturePath(picturePath.replace(IMAGE_PATH_PREFIX, ""));
    }
}
