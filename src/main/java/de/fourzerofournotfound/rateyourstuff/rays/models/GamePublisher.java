package de.fourzerofournotfound.rateyourstuff.rays.models;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name="gamePublishers")
public class GamePublisher extends BaseModel {

    @Column
    private String gamePublisherTitle;

    @OneToMany (mappedBy = "gamePublisher")
    private List<Game> games = new ArrayList<>();

    /*@ManyToMany
    Set<Game> games;*/

    @Builder
    public GamePublisher(String gamePublisherTitle) {
        this.gamePublisherTitle = gamePublisherTitle;
    }
}
