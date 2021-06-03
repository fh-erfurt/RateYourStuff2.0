package de.fourzerofournotfound.rateyourstuff.rays.models;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "Languages")

public class Language extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long languageId;

    @Column(length = 200)
    private String language;

    @ManyToMany
    Set<Medium> media;
}
