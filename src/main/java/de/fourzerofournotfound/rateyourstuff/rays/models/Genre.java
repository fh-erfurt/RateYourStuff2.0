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
@AllArgsConstructor
@RequiredArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "Genres")
public class Genre extends BaseModel {

    @Column(nullable = false, length = 45)
    private String genreName;

    @JsonBackReference
    @ManyToMany
    Set<Medium> media;
}
