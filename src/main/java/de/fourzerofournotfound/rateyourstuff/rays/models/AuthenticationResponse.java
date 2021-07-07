package de.fourzerofournotfound.rateyourstuff.rays.models;

//TODO: to be commented
public class AuthenticationResponse {
    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
