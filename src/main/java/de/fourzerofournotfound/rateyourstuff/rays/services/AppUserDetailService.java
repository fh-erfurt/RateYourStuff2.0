package de.fourzerofournotfound.rateyourstuff.rays.services;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.UserController;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AppUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //return new User("foo", "foo", new ArrayList<>());
        return new User("foo", "foo", new ArrayList<>());
    }
}
