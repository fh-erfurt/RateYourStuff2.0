package de.fourzerofournotfound.rateyourstuff.rays.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "Ratings")
public class Rating extends BaseModel {

    @Column(nullable = false)
    private Integer minimumPoints;

    @Column(nullable = false)
    private Integer maximumPoints;

    @Column(nullable = false)
    private Integer givenPoints;

    @Column(length = 10000)
    private String desscription;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "mediumId", referencedColumnName = "id")
    private Medium medium;

}
