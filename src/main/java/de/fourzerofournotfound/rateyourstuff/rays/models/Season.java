package de.fourzerofournotfound.rateyourstuff.rays.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name="seasons")
public class Season extends BaseModel {

    @Column
    private Integer seasonNumber;

    @Column(length=250)
    private String seasonTitle;

    public Season(Integer seasonNumber, String seasonTitle) {
        this.seasonNumber = seasonNumber;
        this.seasonTitle = seasonTitle;
    }

    @JsonBackReference
    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name = "seriesId", referencedColumnName = "id")
    private Medium medium;

    @JsonBackReference
    @Builder.Default
    @OneToMany (mappedBy = "season", cascade = CascadeType.ALL)
    private Set<Episode> episodes = new HashSet<>();

}
