package de.fourzerofournotfound.rateyourstuff.rays.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * <h1>Network</h1>
 * <p>This Model represents a network.
 * A network is the publisher of a series. (e.g. CBS, CWS, ABC, BBC...)</p>
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name="networks")
public class Network extends BaseModel {

    @Column
    private String networkTitle;

    @OneToMany (mappedBy = "network")
    private List<Series> series;

    @OneToMany (mappedBy = "network")
    private List<Movie> movies;
}
