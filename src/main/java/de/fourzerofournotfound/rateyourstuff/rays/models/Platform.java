package de.fourzerofournotfound.rateyourstuff.rays.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
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
    @ManyToMany
    Set<Medium> games;

    @Builder
    public Platform(String platformTitle) {
        this.platformTitle = platformTitle;
    }
}
