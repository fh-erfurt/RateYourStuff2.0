package de.fourzerofournotfound.rateyourstuff.rays.models.media.series;

import com.fasterxml.jackson.annotation.JsonInclude;
import de.fourzerofournotfound.rateyourstuff.rays.models.BaseModel;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Medium;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.series.Episode;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Season
 * <p>This Model represents a season. A season can ionclue multiple episodes.</p>
 *
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
@Table(name = "seasons")
public class Season extends BaseModel {

    @Column
    private Integer seasonNumber;

    @Column(length = 250)
    private String seasonTitle;

    @JsonInclude
    @Transient
    private Long seriesMappingId;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "seriesId", referencedColumnName = "id")
    private Medium medium;
    @Builder.Default
    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
    private Set<Episode> episodes = new HashSet<>();

    public Season(Integer seasonNumber, String seasonTitle) {
        this.seasonNumber = seasonNumber;
        this.seasonTitle = seasonTitle;
    }

}
