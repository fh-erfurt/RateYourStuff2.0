package de.fourzerofournotfound.rateyourstuff.rays.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * <h1>Platform</h1>
 * <p>This Model represents a platform. A platform defines the device on which a game can be played.</p>
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
@Table(name = "Platforms")
public class Platform extends BaseModel {

    @Column (length = 250)
    private String platformTitle;

    @JsonBackReference
    @ManyToMany
    Set<Medium> games;

    @Builder
    public Platform(String platformTitle) {
        this.platformTitle = platformTitle;
    }
}
