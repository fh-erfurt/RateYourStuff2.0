package de.fourzerofournotfound.rateyourstuff.rays.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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
