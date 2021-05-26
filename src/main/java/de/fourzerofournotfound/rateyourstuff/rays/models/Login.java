package de.fourzerofournotfound.rateyourstuff.rays.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "Logins")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loginId;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(nullable = true)
    @ColumnDefault("NULL ON UPDATE CURRENT_TIMESTAMP()")
    private LocalDateTime updatedAt;

    @Column
    private LocalDateTime lastLogin;

    //TODO: Why should "isEnabled" be false from the beginning?
    // Is it because of the user have to validate his mail before the account will be enabled?
    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean isEnabled;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @ColumnDefault("0")
    @Builder.Default
    private Integer failedLoginCount = 0;

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
