package de.fourzerofournotfound.rateyourstuff.RaYS.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    @ColumnDefault("CURRENT_TIMESTAMP()")
    private LocalDateTime createdAt;

    @Column
    @ColumnDefault("NULL ON UPDATE CURRENT_TIMESTAMP()")
    private LocalDateTime updatedAt;

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

    @OneToOne
    @JoinColumn(name = "login_Id", referencedColumnName = "loginId")
    private Login login;

    @OneToMany(mappedBy = "user")
    private Set<Raiting> userRatings;

    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "user")
    private Set<Progress> progresses;

    @OneToMany(mappedBy = "user")
    private Set<Collection> collections;

    @ManyToMany
    private Set<User> friendList;

}
