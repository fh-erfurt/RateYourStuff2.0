package de.fourzerofournotfound.rateyourstuff.rays.models;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "Roles")
public class Role extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(nullable = false)
    private String roleName;

    @OneToMany(mappedBy = "role")
    private List<LoginRole> loginRoles;

}
