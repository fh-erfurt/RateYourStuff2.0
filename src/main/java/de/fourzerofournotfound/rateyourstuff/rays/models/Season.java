package de.fourzerofournotfound.rateyourstuff.rays.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name="seasons")
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seasonId;

    @CreatedDate
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    //@ColumnDefault("CURRENT_TIMESTAMP()")
    private LocalDateTime createdAt;

    @Column
    @ColumnDefault("NULL ON UPDATE CURRENT_TIMESTAMP()")
    private LocalDateTime updatedAt;

    @Column
    private Integer seasonNumber;

    @Column(length=250)
    private String seasonTitle;

    public Season(Integer seasonNumber, String seasonTitle) {
        this.seasonNumber = seasonNumber;
        this.seasonTitle = seasonTitle;
    }

    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name = "mediumId", referencedColumnName = "mediumId")
    private Medium medium;

    @OneToMany (mappedBy = "season", cascade = CascadeType.PERSIST)
    private Set<Episode> episodes = new HashSet<>();


}
