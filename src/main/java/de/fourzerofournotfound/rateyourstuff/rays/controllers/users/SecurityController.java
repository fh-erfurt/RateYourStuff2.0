package de.fourzerofournotfound.rateyourstuff.rays.controllers.users;

import de.fourzerofournotfound.rateyourstuff.rays.models.users.User;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.users.UserRepository;
import de.fourzerofournotfound.rateyourstuff.rays.security.AuthenticationRequest;
import de.fourzerofournotfound.rateyourstuff.rays.security.AuthenticationResponse;
import de.fourzerofournotfound.rateyourstuff.rays.services.users.AppUserDetailService;
import de.fourzerofournotfound.rateyourstuff.rays.services.users.UserService;
import de.fourzerofournotfound.rateyourstuff.rays.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * <p>This Controller provides basic REST Interfaces to interact with User and Login entities from the database and
 * some Methods of spring security to provide a functional security.</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */

@RestController
public class SecurityController {

    User validUser;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AppUserDetailService appUserDetailService;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    /**
     * Method is used for authenticate users and generates a java web token (JWT)
     *
     * @param authenticationRequest given data to authenticate User
     * @return {@link AuthenticationResponse authenticationResponse } included the generated JWT
     * @throws BadCredentialsException in case of bad login data inputs
     */
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws BadCredentialsException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password", e);
        }
        final UserDetails userDetails = appUserDetailService
                .loadUserByUsername(authenticationRequest.getUsername());

        Optional<User> user = userRepository.findByUserName(authenticationRequest.getUsername());
        //Save Optional User in User Object validUser
        user.ifPresent(value -> validUser = value);

        final String jwt = jwtTokenUtil.generateToken(userDetails, validUser);

        /**
         * Condition is checking if the to authenticated user is enabled;
         * @throws AccessDeniedException if the user isn´t enabled
         * @return JWT-Token
         */
        Optional<User> potentialUser = userRepository.findByUserName(authenticationRequest.getUsername());
        if(potentialUser.isPresent() && potentialUser.get().getLogin().getIsEnabled()){
            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        } else {
            throw new AccessDeniedException("User isn´t enabled");
        }

    }
}
