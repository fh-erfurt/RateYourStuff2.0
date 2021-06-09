package de.fourzerofournotfound.rateyourstuff.rays.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "LoginRoles")
public class LoginRole extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "loginID", referencedColumnName = "id")
    private Login login;

    @ManyToOne
    @JoinColumn(name = "roleId", referencedColumnName = "id")
    private Role role;

}
