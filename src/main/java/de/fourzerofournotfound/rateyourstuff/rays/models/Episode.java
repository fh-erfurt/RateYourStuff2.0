package de.fourzerofournotfound.rateyourstuff.rays.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name = "Episodes")
public class Episode extends Medium {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long episodeId;

    @Column(nullable = false)
    @ColumnDefault("CURRENT_TIMESTAMP()")
    private LocalDateTime createdAt;

    @Column
    @ColumnDefault("NULL ON UPDATE CURRENT_TIMESTAMP()")
    private LocalDateTime updatedAt;

    @Column
    private Integer episodeNumber;

    @Column
    private Integer length;

    @ManyToOne
    @JoinColumn(name = "seasonId", referencedColumnName = "seasonId")
    private Season season;
}

