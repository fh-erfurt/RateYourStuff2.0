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
@Table(name = "Series")
public class Series extends Medium{


    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long seriesId;

    @Column(nullable = false)
    @ColumnDefault("CURRENT_TIMESTAMP()")
    private LocalDateTime createdAt;

    @Column
    @ColumnDefault("NULL ON UPDATE CURRENT_TIMESTAMP()")
    private LocalDateTime updatedAt;


    @Column
    private Integer averageLength;

    //TODO: a single network can have multiple series, thus network should be an extra table
    //@Column(nullable = false)
    //private String network;
    @ManyToOne
    @JoinColumn(name = "networkId", referencedColumnName = "networkId")
    private Network network;

    //TODO: it does not really make sense to have a highest resolution. An aspect ratio would be more fitting
    //@Column(nullable = false)
    //private String highestResolution;

    @Column(nullable = false)
    private Integer ageRestriction;

    @Column(nullable = false)
    private Boolean isCompleted;

    //@OneToOne
    //@JoinColumn(name = "mediumId", referencedColumnName = "mediumId")
    //private Medium medium;

    @OneToMany (mappedBy = "medium")
    private Set<Season> seasons;
}
