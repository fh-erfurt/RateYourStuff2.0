package de.fourzerofournotfound.rateyourstuff.rays.models.media.games;

import de.fourzerofournotfound.rateyourstuff.rays.models.BaseModel;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.games.Game;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Platform
 * <p>This Model represents a platform. A platform defines the device on which a game can be played.</p>
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
@Table(name = "Platforms")
public class Platform extends BaseModel {

    @ManyToMany(mappedBy = "platforms")
    Set<Game> games;
    @Column(length = 250)
    private String platformTitle;

    @Builder
    public Platform(String platformTitle) {
        this.platformTitle = platformTitle;
    }
}
