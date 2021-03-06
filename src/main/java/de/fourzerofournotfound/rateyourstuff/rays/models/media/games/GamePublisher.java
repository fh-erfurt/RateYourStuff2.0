package de.fourzerofournotfound.rateyourstuff.rays.models.media.games;

import de.fourzerofournotfound.rateyourstuff.rays.models.BaseModel;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.games.Game;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>This Model represents a game publisher. Each game can have one publisher</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "gamePublishers")
public class GamePublisher extends BaseModel {

    @Column
    private String gamePublisherTitle;

    @OneToMany(mappedBy = "gamePublisher")
    private List<Game> games = new ArrayList<>();

    @Builder
    public GamePublisher(String gamePublisherTitle) {
        this.gamePublisherTitle = gamePublisherTitle;
    }
}
