package de.fourzerofournotfound.rateyourstuff.rays.models;

import com.fasterxml.jackson.annotation.*;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Medium;
import de.fourzerofournotfound.rateyourstuff.rays.models.users.User;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Comment
 * <p>This Model represents a Comment. A Comment is assigned to a medium.
 * A comment can also have a parent comment.</p>
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
@Table(name = "Comments")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Comment extends BaseModel {

    @Column(nullable = false, length = 10000)
    private String textOfComment;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "mediumId", referencedColumnName = "id")
    private Medium medium;

    @OneToMany(mappedBy = "commentParent", fetch= FetchType.LAZY)
    private Set<Comment> commentChildren;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "commentParent")
    private Comment commentParent;

    @JsonInclude()
    @Transient
    private Long mediumMappingId;

    @JsonInclude()
    @Transient
    private Long userMappingId;

    @JsonInclude()
    @Transient
    private Long parentMappingId;
}
