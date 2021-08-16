package de.fourzerofournotfound.rateyourstuff.rays.services;

import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import de.fourzerofournotfound.rateyourstuff.rays.models.Role;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.RoleRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.jdbc.support.incrementer.AbstractDataFieldMaxValueIncrementer;
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
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AppUserDetailService implements UserDetailsService {

    private static RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;



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
        if(user.getRole().getRoleName().equals("Admin")) {
            authenticationList.add("Admin");
            authenticationList.add("Moderator");
            authenticationList.add("User");
        } else if(user.getRole().getRoleName().equals("Moderator")) {
            authenticationList.add("Moderator");
            authenticationList.add("User");
        } else if(user.getRole().getRoleName().equals("User")){
            authenticationList.add("User");
        }
       /*System.out.println("Current UserRole: " + user.getRole().getRoleName());
       System.out.println("AuthorityList: ");
        for(String role : authenticationList) {
            System.out.println(role);
        }*/
        String[] userRoles = authenticationList.toArray(new String[0]);
       return AuthorityUtils.createAuthorityList(userRoles);
   }
}