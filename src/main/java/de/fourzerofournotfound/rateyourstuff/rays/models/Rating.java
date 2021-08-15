package de.fourzerofournotfound.rateyourstuff.rays.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Medium;
import lombok.*;

import javax.persistence.*;

/**
 * Rating
 * <p>This Model represents a Rating.</p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "Ratings")
public class Rating extends BaseModel {

    public final static  int MIN_POINTS = 0;

    public final static int MAX_POINTS = 10;

    @Column(nullable = false)
    private Integer givenPoints;

    @Column(length = 10000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "mediumId", referencedColumnName = "id")
    private Medium medium;

    @JsonInclude()
    @Transient
    private Long mediumMappingId;

    @JsonInclude()
    @Transient
    private Long userMappingId;

}
