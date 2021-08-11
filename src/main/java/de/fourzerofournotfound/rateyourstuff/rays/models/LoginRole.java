package de.fourzerofournotfound.rateyourstuff.rays.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/**
 * <h1>Login Role</h1>
 * <p>This Model represents a login role. The login role connects the login and the roles.
 * It allows a single login to have multiple roles</p>
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "LoginRoles")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class LoginRole extends BaseModel {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loginID", referencedColumnName = "id")
    private Login login;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roleId", referencedColumnName = "id")
    private Role role;

}
