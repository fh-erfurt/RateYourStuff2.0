package de.fourzerofournotfound.rateyourstuff.rays.controllers.users;

import de.fourzerofournotfound.rateyourstuff.rays.models.users.AuthenticationRequest;
import de.fourzerofournotfound.rateyourstuff.rays.models.users.AuthenticationResponse;
import de.fourzerofournotfound.rateyourstuff.rays.models.users.User;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.users.UserRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.users.AppUserDetailService;
import de.fourzerofournotfound.rateyourstuff.rays.services.users.UserService;
import de.fourzerofournotfound.rateyourstuff.rays.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class SecurityController {

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

    User validUser;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch(BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = appUserDetailService
                .loadUserByUsername(authenticationRequest.getUsername());

        Optional<User> user = userRepository.findByUserName(authenticationRequest.getUsername());
        //Save Optional User in User Object validUser
        user.ifPresent(value -> validUser = value);


        final String jwt = jwtTokenUtil.generateToken(userDetails, validUser);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}