package de.fourzerofournotfound.rateyourstuff.rays.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "Platforms")
public class Platform extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long platformId;

    @Column (length = 250)
    private String platformTitle;

    @OneToMany (mappedBy = "platform")
    private List<Game> games;

    @ManyToMany
    Set<Medium> media;

    public Platform(String platformTitle) {
        this.platformTitle = platformTitle;
    }

}
