package de.fourzerofournotfound.rateyourstuff.rays.security;

/**
 * <p>This Class represents a Authentication Response. A Authentication Response used to provides all data for
 * the Response of Java-Web-Token for the future authentication of users which send this token back later</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */

public class AuthenticationResponse {
    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
