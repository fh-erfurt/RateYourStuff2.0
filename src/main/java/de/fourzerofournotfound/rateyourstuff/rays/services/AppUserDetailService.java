package de.fourzerofournotfound.rateyourstuff.rays.services;

import de.fourzerofournotfound.rateyourstuff.rays.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AppUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<de.fourzerofournotfound.rateyourstuff.rays.models.User> optionalUser = userRepository.findByUserName(userName);
        if(optionalUser.isPresent())
        {
            return new User(optionalUser.get().getUserName(), optionalUser.get().getLogin().getPasswordHash(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("No available User for given userName");
        }
    }
}
