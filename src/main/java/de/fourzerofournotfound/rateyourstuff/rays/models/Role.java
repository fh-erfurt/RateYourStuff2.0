package de.fourzerofournotfound.rateyourstuff.rays.models;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "Roles")
public class Role extends BaseModel {

    @Column(nullable = false)
    private String roleName;

    @OneToMany(mappedBy = "role")
    private List<LoginRole> loginRoles;

}
