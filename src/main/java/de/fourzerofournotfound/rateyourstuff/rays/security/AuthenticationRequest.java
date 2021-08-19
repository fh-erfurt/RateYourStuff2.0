package de.fourzerofournotfound.rateyourstuff.rays.security;

/**
 * <p>This Class represents a Authentication Request. A Authentication Request used provides all data for
 * the authentication in the Security Controller and Security Filters</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */

public class AuthenticationRequest {

    private String username;
    private String password;

    public AuthenticationRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
