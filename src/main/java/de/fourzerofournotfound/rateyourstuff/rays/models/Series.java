package de.fourzerofournotfound.rateyourstuff.rays.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "mediumId")
@Table(name = "Series")
public class Series extends Medium {

    @Column
    private Integer averageLength;

    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name = "networkId", referencedColumnName = "networkId")
    private Network network;

    @Column(nullable = false)
    private Integer ageRestriction;

    @Column(nullable = false)
    private Boolean isCompleted;

    @OneToMany (mappedBy = "medium", cascade = CascadeType.ALL)
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
}
