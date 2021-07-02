package de.fourzerofournotfound.rateyourstuff.rays.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "Platforms")
public class Platform extends BaseModel {

    @Column (length = 250)
    private String platformTitle;

    @JsonBackReference
    @OneToMany (mappedBy = "platform")
    private List<Game> games;

    @Builder
    public Platform(String platformTitle) {
        this.platformTitle = platformTitle;
    }
}
