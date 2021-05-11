package de.fourzerofournotfound.rateyourstuff.rays.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name = "Movies")
public class Movie extends Medium{


    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long movieId;

    @Column(nullable = false)
    @ColumnDefault("CURRENT_TIMESTAMP()")
    private LocalDateTime createdAt;

    @Column
    @ColumnDefault("NULL ON UPDATE CURRENT_TIMESTAMP()")
    private LocalDateTime updatedAt;

    @Column
    private Integer length;

    @Column
    private Integer ageRestriction;

    @ManyToOne
    @JoinColumn(name = "networkId", referencedColumnName = "networkId")
    private Network network;




    //@OneToOne
    //@JoinColumn(name = "mediumId", referencedColumnName = "mediumId")
    //private Medium medium;

}
