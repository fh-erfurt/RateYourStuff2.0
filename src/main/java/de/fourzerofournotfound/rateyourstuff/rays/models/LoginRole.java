package de.fourzerofournotfound.rateyourstuff.rays.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "LoginRoles")
public class LoginRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loginRoleId;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(nullable = true)
    @ColumnDefault("NULL ON UPDATE CURRENT_TIMESTAMP()")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "loginID", referencedColumnName = "loginID")
    private Login login;

    @ManyToOne
    @JoinColumn(name = "roleId", referencedColumnName = "roleId")
    private Role role;

}
