package de.fourzerofournotfound.rateyourstuff.rays.models;

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
@Table(name = "Movies")
public class Movie extends Medium {

    @Column(nullable = false)
    @ColumnDefault("CURRENT_TIMESTAMP()")
    private LocalDateTime createdAt;

    @Column
    @ColumnDefault("NULL ON UPDATE CURRENT_TIMESTAMP()")
    private LocalDateTime updatedAt;

    @Column
    private Integer length;

    @Column
    private Integer ageRestriction;

    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name = "networkId", referencedColumnName = "networkId")
    private Network network;

    public Movie(String mediumName, String shortDescription, LocalDate releaseDate, Integer length, Integer ageRestriction) {
        this.setMediumName(mediumName);
        this.setShortDescription(shortDescription);
        this.setReleaseDate(releaseDate);
        this.length = length;
        this.ageRestriction = ageRestriction;
    }


    //@OneToOne
    //@JoinColumn(name = "mediumId", referencedColumnName = "mediumId")
    //private Medium medium;

}
