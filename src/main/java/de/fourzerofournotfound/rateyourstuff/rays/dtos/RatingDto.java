package de.fourzerofournotfound.rateyourstuff.rays.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

/**
 * <p>The Rating DTO is used to provide reduced information to the client.</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Getter
@Setter
public class RatingDto {

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    //written in screaming snake case because it is a final attribute in the model
    private int MAX_POINTS;

    //written in screaming snake case because it is a final attribute in the model
    private int MIN_POINTS;

    private Long id;

    private Integer givenPoints;

    private String description;

    private Long mediumId;

    private Long userId;

    private String userUserName;

    private String mediumMediumName;

    private String createdAt;

}
