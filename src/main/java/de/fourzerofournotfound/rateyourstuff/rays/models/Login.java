package de.fourzerofournotfound.rateyourstuff.rays.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @OneToMany(mappedBy = "login")
    private List<LoginRole> loginRoles;

}
