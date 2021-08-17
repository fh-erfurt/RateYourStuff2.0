package de.fourzerofournotfound.rateyourstuff.rays.services;

import de.fourzerofournotfound.rateyourstuff.rays.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import de.fourzerofournotfound.rateyourstuff.rays.models.User;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class AppUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public AppUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


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