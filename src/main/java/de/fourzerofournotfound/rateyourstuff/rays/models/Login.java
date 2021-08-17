package de.fourzerofournotfound.rateyourstuff.rays.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Login
 * <p>This Model represents a Login. A login contains the login information for a specific user, such as the password</p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "Logins")
public class Login extends BaseModel {

    @Column
    private LocalDateTime lastLogin;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean isEnabled;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @ColumnDefault("0")
    @Builder.Default
    private Integer failedLoginCount = 0;

    @Column(nullable = false)
    private String passwordHash;

    @Column()
    private String passwordResetHash;

    @Column
    private LocalDateTime passwordResetCreatedAt;

    @OneToOne(mappedBy = "login")
    private User user;

}
