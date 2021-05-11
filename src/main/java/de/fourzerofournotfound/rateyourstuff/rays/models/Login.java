package de.fourzerofournotfound.rateyourstuff.rays.models;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "Logins")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loginId;

    @Column(nullable = false)
    @ColumnDefault("CURRENT_TIMESTAMP()")
    private LocalDateTime createdAt;

    @Column(nullable = true)
    @ColumnDefault("NULL ON UPDATE CURRENT_TIMESTAMP()")
    private LocalDateTime updatedAt;

    @Column
    private LocalDateTime lastLogin;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean isEnabled;

    @Column
    private String email;

    @Column(length = 45, nullable = false)
    @ColumnDefault("'user'")
    private String userRole;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer failedLoginCount;

    @Column(nullable = false, length = 255)
    private String passwordHash;

    @Column(length = 255)
    private String passwordResetHash;

    @Column
    private LocalDateTime passwordResetCreatedAt;

    @OneToOne(mappedBy = "login")
    private User user;

    @OneToMany(mappedBy = "login")
    private List<LoginRole> loginRoles;

}
