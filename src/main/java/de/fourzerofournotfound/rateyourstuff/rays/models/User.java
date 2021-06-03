package de.fourzerofournotfound.rateyourstuff.rays.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "Users")
public class User extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

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

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "login_Id", referencedColumnName = "loginId")
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
