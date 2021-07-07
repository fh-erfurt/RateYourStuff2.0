package de.fourzerofournotfound.rateyourstuff.rays.services;

import de.fourzerofournotfound.rateyourstuff.rays.models.Login;
import de.fourzerofournotfound.rateyourstuff.rays.models.User;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.UserNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.LoginRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.UserRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.InvalidLoginException;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.InvalidUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("us")
public class UserService {
    private final UserRepository userRepository;
    private final LoginRepository loginRepository;

    @Autowired
    public UserService(UserRepository userRepository, LoginRepository loginRepository) {
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
    }

    /**
     * Both Mehtods, addReferenceToUser and addReferenceToLogin saves Login ID and Created at if user will be updated in database
     * @param user from api
     * @return User object
     * @throws InvalidUserException if UserId isn´t valid.
     */
    public User addReferencesToUser(User user) throws InvalidUserException {
        Optional<User> tempUser = userRepository.findById(user.getId());
        Optional<Login> tempLogin = loginRepository.findById(tempUser.get().getLogin().getId());
        if(tempLogin.isPresent()){
            user.setLogin(tempLogin.get());
            user.setCreatedAt(tempUser.get().getCreatedAt());
            return user;
        } else {
            throw new InvalidUserException("The given user must have a valid userId and a valid loginId");
        }
    }

    public Login addReferencesToLogin(Login login) throws InvalidLoginException {
        Optional<Login> tempLogin = loginRepository.findById(login.getId());
        if(tempLogin.isPresent())
        {
            login.setCreatedAt(tempLogin.get().getCreatedAt());
            return login;
        } else {
            throw new InvalidLoginException("The given login must have a valid loginId");
        }
    }

    public Long getIdByUser(Optional<User> user) throws UserNotFoundException {
        if(user.isPresent()){
            return user.get().getId();
        } else {
            throw new UserNotFoundException("No valid user!");
        }
    }

}
