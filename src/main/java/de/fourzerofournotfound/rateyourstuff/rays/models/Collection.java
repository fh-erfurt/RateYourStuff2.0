package de.fourzerofournotfound.rateyourstuff.rays.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "Collections")
public class Collection extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long collectionId;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userid")
    private User user;

    @ManyToMany
    Set<Medium> media;
}
