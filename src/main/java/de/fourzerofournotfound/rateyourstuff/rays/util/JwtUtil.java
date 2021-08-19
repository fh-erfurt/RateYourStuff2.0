package de.fourzerofournotfound.rateyourstuff.rays.util;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.users.SecurityController;
import de.fourzerofournotfound.rateyourstuff.rays.models.users.User;
import de.fourzerofournotfound.rateyourstuff.rays.security.JwtRequestFilter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * <p>This Service provides methods to the following classes:</p>
 * {@link JwtRequestFilter JwtRequestFilter}
 * {@link SecurityController SecurityController}
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */

@Service
public class JwtUtil {

    private final String SECRET_KEY = "secret";

    /**
     * Method gets an claim object of Jwt-Token and Username
     *
     * @param token given encoded jwt-token
     * @return String Object of extractClaim with the token and the extracted Username
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Method gets an claim object of Jwt-Token and expiration Time
     *
     * @param token given encoded jwt-token
     * @return Date Object of claim which contains the token and the expirationClaim
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Method is used to extract claims
     *
     * @param token          given jwt-token
     * @param claimsResolver class of spring security to resolve the claims
     * @param <T>            Custom Claim Objects
     * @return Object of resolved claims
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Method to extract all given claims
     *
     * @param token given jwt-token
     * @return Claims Object with all Claims
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    /**
     * Method is used to check if the given token is still expired
     *
     * @param token given jwt-token
     * @return true if the expiration is valid
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Method is used to collect all claims and create the JWT-Token-String with all important data
     *
     * @param userDetails for given Username
     * @param user        for different claims
     * @return String which contains the jwt-token
     */
    public String generateToken(UserDetails userDetails, User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("role", user.getRole().getRoleName());
        return createToken(claims, userDetails.getUsername());
    }

    /**
     * Method creates given token with expiration and encodes the JWT in HS256 Algorithm
     *
     * @param claims  given in method generateToken and contains the given claims
     * @param subject given in method generateToken and contains the user subject
     * @return String which contains the jwt-token
     */
    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    /**
     * Method Compares the extracted Username of the given jwt-token with the user which is logged in
     *
     * @param token       String which contains the jwt-token
     * @param userDetails String which contains the user which currently requests
     * @return true if the token contains the given user
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
