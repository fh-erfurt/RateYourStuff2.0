package de.fourzerofournotfound.rateyourstuff.rays.services.users;

import de.fourzerofournotfound.rateyourstuff.rays.controllers.users.LoginController;
import de.fourzerofournotfound.rateyourstuff.rays.dtos.users.LoginDto;
import de.fourzerofournotfound.rateyourstuff.rays.errors.users.EmailAlreadyExistsException;
import de.fourzerofournotfound.rateyourstuff.rays.models.users.Login;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.users.LoginRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>This Service provides methods to the following class:</p>
 * {@link LoginController LoginController}
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */

@Service("ls")
public class LoginService {

    private final ModelMapper modelMapper;
    private final UserSecurityService userSecurityService;

    @Autowired
    private LoginRepository loginRepository;

    LoginService(ModelMapper mapper, UserSecurityService secService) {
        modelMapper = mapper;
        userSecurityService = secService;
    }

    /**
     * Method converts a given Login object to a custom Login-DTO
     *
     * @param login given login object
     * @return Login-DTO
     */
    public LoginDto convertToDto(Login login) {
        return modelMapper.map(login, LoginDto.class);
    }

    public boolean isValidEmail(String email) throws EmailAlreadyExistsException {
        Optional<Login> potentialLogin = loginRepository.findLoginByEmailIgnoreCase(email);
        if (potentialLogin.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        } else {
            return true;
        }
    }

    /**
     * Method manages 4 different situations of the account update to save the persistence of the Login entity
     * By changing separate data instead of all data, the Controller needs this method to handle the Update
     *
     * @param login          given updated login which comes from the web-form
     * @param potentialLogin copy of the same login from database to keep data which won´t be updated
     * @throws EmailAlreadyExistsException if the user want to change the email but the email is already existing
     */
    public void manageUpdatePersistence(Login login, Optional<Login> potentialLogin) throws EmailAlreadyExistsException {
        if (!login.getEmail().equals(potentialLogin.get().getEmail()) && !login.getPasswordHash().equals("DUMMY"))               //ne | neq
        {
            if(isValidEmail(login.getEmail())){
                potentialLogin.get().setEmail(login.getEmail());
                potentialLogin.get().setPasswordHash(login.getPasswordHash());
                userSecurityService.hashPasswordOfLogin(potentialLogin.get());
            } else {
                potentialLogin.get().setPasswordHash(login.getPasswordHash());
                userSecurityService.hashPasswordOfLogin(potentialLogin.get());
                throw new EmailAlreadyExistsException("Email already exists");
            }

        } else if (!login.getEmail().equals(potentialLogin.get().getEmail()) && login.getPasswordHash().equals("DUMMY")) {      //neq | eq
            if(isValidEmail(login.getEmail())){
                potentialLogin.get().setEmail(login.getEmail());
            } else {
                throw new EmailAlreadyExistsException("Email already exists");
            }
        } else if (login.getEmail().equals(potentialLogin.get().getEmail()) && !login.getPasswordHash().equals("DUMMY")) {      //eq  | neq
            potentialLogin.get().setPasswordHash(login.getPasswordHash());
            userSecurityService.hashPasswordOfLogin(potentialLogin.get());
        }

    }
}
