package de.fourzerofournotfound.rateyourstuff.rays.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>GamePublisher</h1>
 * <p>This Model represents a game publisher. Each game can have one publisher</p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name="gamePublishers")
public class GamePublisher extends BaseModel {

    @Column
    private String gamePublisherTitle;

    //@JsonBackReference
    @OneToMany (mappedBy = "gamePublisher")
    private List<Game> games = new ArrayList<>();

    @Builder
    public GamePublisher(String gamePublisherTitle) {
        this.gamePublisherTitle = gamePublisherTitle;
    }
}
