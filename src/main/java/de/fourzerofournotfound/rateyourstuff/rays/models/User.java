package de.fourzerofournotfound.rateyourstuff.rays.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Collection;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * User
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "Users")
public class User extends BaseModel {

    public final static String IMAGE_PATH_PREFIX = "images/users/";

    @Column(length = 200)
    private String firstName;

    @Column(length = 200)
    private String lastName;

    @Column(length = 200)
    private String secondName;

    @Column(length = 200, nullable = false, unique = true)
    private String userName;

    @Column(length = 45, nullable = false)
    private String gender;

    @Column(length = 256)
    private String profilePicturePath;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loginId", referencedColumnName = "id", nullable = false)
    private Login login;

    @OneToMany(mappedBy = "user")
    private Set<Rating> userRatings;

    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "user")
    private Set<Collection> collections;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="roleId", referencedColumnName = "id", nullable = false)
    private Role role;

    @JsonInclude
    @Transient
    private Long roleMappingId;

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath.replace(IMAGE_PATH_PREFIX, "");
    }

    public String getProfilePicturePath() {
        if(profilePicturePath != null) {
            return IMAGE_PATH_PREFIX + this.profilePicturePath;
        }
        return null;
    }
}
