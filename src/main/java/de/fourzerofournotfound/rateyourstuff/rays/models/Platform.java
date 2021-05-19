package de.fourzerofournotfound.rateyourstuff.rays.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name = "Platforms")
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long platformId;

    @Column(nullable = false)
    @ColumnDefault("CURRENT_TIMESTAMP()")
    private LocalDateTime createdAt;

    @Column
    @ColumnDefault("NULL ON UPDATE CURRENT_TIMESTAMP()")
    private LocalDateTime updatedAt;

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
