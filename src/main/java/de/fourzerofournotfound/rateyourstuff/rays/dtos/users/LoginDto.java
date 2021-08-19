package de.fourzerofournotfound.rateyourstuff.rays.dtos.users;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>The Login DTO is used to provide reduced information to the client.</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */

@Setter
@Getter
public class LoginDto {
    private Long id;
    private String email;
    private String passwordHashed;
}
