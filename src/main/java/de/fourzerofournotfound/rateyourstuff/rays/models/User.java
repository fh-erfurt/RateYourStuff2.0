package de.fourzerofournotfound.rateyourstuff.rays.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "Users")
public class User extends BaseModel {

    @Column(length = 200)
    private String firstName;

    @Column(length = 200)
    private String lastName;

    @Column(length = 200, nullable = true) //Mark: Changed to nullable | 26.05.2021
    private String secondName;

    @Column(length = 200, nullable = false, unique = true)
    private String userName;

    @Column(length = 45, nullable = false)
    private String gender;

    @Column(length = 256)
    private String profilePicturePath;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loginId", referencedColumnName = "id")
    private Login login;

    @OneToMany(mappedBy = "user")
    private Set<Rating> userRatings;

    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "user")
    private Set<Progress> progresses;

    @OneToMany(mappedBy = "user")
    private Set<Collection> collections;

    @ManyToMany
    private Set<User> friendList;

/*
    public User(String firstName, String lastName, String secondName, String userName, String gender)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondName = secondName;
        this.userName = userName;
        this.gender = gender;
    }

 */
}
