package de.fourzerofournotfound.rateyourstuff.rays.models.media;

import de.fourzerofournotfound.rateyourstuff.rays.models.BaseModel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Network
 * <p>This Model represents a network.
 * A network is the publisher of a series. (e.g. CBS, CWS, ABC, BBC...)</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "networks")
public class Network extends BaseModel {

    @Column
    private String networkTitle;

    @OneToMany(mappedBy = "network")
    private List<Series> series;

    @OneToMany(mappedBy = "network")
    private List<Movie> movies;
}
