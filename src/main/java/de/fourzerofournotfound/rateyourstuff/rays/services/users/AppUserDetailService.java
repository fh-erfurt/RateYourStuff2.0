package de.fourzerofournotfound.rateyourstuff.rays.services.users;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.users.SecurityController;
import de.fourzerofournotfound.rateyourstuff.rays.core.SecurityConfiguration;
import de.fourzerofournotfound.rateyourstuff.rays.models.users.User;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.users.UserRepository;
import de.fourzerofournotfound.rateyourstuff.rays.security.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * <p>This Service provides methods to the following classes:</p>
 * {@link SecurityController SecurityController}
 * {@link SecurityConfiguration SecurityConfiguration}
 * {@link JwtRequestFilter JwtRequestFilter}
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */

@Service
@Transactional
public class AppUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public AppUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     * Method instantiate a new user in security context with its username, password and its Authority List
     * @param userName to find in database
     * @return a new user in security context (not equal to the user from models)
     * @throws UsernameNotFoundException if the given user was not found in database
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUserName(userName);
        if(optionalUser.isPresent())
        {
            return new org.springframework.security.core.userdetails.User
                    (optionalUser.get().getUserName(), optionalUser.get().getLogin().getPasswordHash(), getAuthorities(optionalUser.get()));
        } else {
            throw new UsernameNotFoundException("No available User for given userName");
        }
    }

    /**
     * Method provides a Collection of authorities for a given user
     * @param user given to check its role to decide which permissions he will get
     * @return Object of an AuthorityList
     */
   private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        ArrayList<String> authenticationList = new ArrayList<>();
       switch (user.getRole().getRoleName()) {
           case "Admin":
               authenticationList.add("Admin");
               authenticationList.add("Moderator");
               authenticationList.add("User");
               break;
           case "Moderator":
               authenticationList.add("Moderator");
               authenticationList.add("User");
               break;
           case "User":
               authenticationList.add("User");
               break;
       }

        String[] userRoles = authenticationList.toArray(new String[0]);
       return AuthorityUtils.createAuthorityList(userRoles);
   }
}