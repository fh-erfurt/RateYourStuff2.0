package de.fourzerofournotfound.rateyourstuff.rays.dtos.media;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.fourzerofournotfound.rateyourstuff.rays.models.GamePublisher;
import de.fourzerofournotfound.rateyourstuff.rays.models.Platform;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
public class GameDto extends MediumDto{

    private Float averagePlaytime;

    private Integer minNumberOfGamers;

    private Integer maxNumberOfGamers;

    private Platform platform;

    private GamePublisher gamePublisher;

    private Integer ageRestriction;
}
