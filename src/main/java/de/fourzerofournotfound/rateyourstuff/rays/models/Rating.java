package de.fourzerofournotfound.rateyourstuff.rays.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Table(name = "Ratings")
public class Rating extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;

    @Column(nullable = false)
    private Integer minimumPoints;

    @Column(nullable = false)
    private Integer maximumPoints;

    @Column(nullable = false)
    private Integer givenPoints;

    @Column(length = 10000)
    private String desscription;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "mediumId", referencedColumnName = "mediumId")
    private Medium medium;

}
