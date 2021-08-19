package de.fourzerofournotfound.rateyourstuff.rays.dtos.users;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>The User DTO is used to provide reduced information to the client.</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */

@Setter
@Getter
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private Long loginId;
    private String loginEmail;
    private Boolean loginIsEnabled;
    private Long roleId;
}
