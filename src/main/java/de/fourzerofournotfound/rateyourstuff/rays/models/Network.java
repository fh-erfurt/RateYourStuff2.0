package de.fourzerofournotfound.rateyourstuff.rays.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name="networks")
public class Network {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long networkId;

    @Column
    private String networkTitle;

    @OneToMany (mappedBy = "network")
    private List<Series> series;

    @OneToMany (mappedBy = "network")
    private List<Movie> movies;

}
