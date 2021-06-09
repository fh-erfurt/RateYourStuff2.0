package de.fourzerofournotfound.rateyourstuff.rays.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Table(name = "Comments")
public class Comment extends BaseModel {

    @Column(nullable = false, length = 10000)
    private String textOfComment;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "mediumId", referencedColumnName = "id")
    private Medium medium;

    @OneToMany(mappedBy = "commentParent")
    private Set<Comment> commentChildren;

    @ManyToOne
    @JoinColumn(name = "commentParent")
    private Comment commentParent;

}
