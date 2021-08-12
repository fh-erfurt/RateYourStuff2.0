package de.fourzerofournotfound.rateyourstuff.rays.models.media;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import de.fourzerofournotfound.rateyourstuff.rays.models.BaseModel;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * <h1>Season</h1>
 * <p>This Model represents a season. A season can ionclue multiple episodes.</p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Getter
@Setter
@Builder
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name="seasons")
public class Season extends BaseModel {

    @Column
    private Integer seasonNumber;

    @Column(length=250)
    private String seasonTitle;

    @JsonInclude
    @Transient
    private Long seriesMappingId;

    public Season(Integer seasonNumber, String seasonTitle) {
        this.seasonNumber = seasonNumber;
        this.seasonTitle = seasonTitle;
    }

    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name = "seriesId", referencedColumnName = "id")
    private Medium medium;

    @JsonBackReference
    @Builder.Default
    @OneToMany (mappedBy = "season", cascade = CascadeType.ALL)
    private Set<Episode> episodes = new HashSet<>();

}