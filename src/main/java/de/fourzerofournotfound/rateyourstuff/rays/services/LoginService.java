package de.fourzerofournotfound.rateyourstuff.rays.services;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.LoginDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Login;
import de.fourzerofournotfound.rateyourstuff.rays.services.errors.EmailAlreadyExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("ls")
public class LoginService {

    private final ModelMapper modelMapper;
    private final UserSecurityService userSecurityService;

    LoginService(ModelMapper mapper, UserSecurityService secService) {
        modelMapper = mapper;
        userSecurityService = secService;
    }


    public LoginDto convertToDto(Login login) {
        return modelMapper.map(login, LoginDto.class);
    }

    public void manageUpdatePersistence(Login login, Optional<Login> potentialLogin) throws EmailAlreadyExistsException {
        //Equal email addresses and not identical password
        if(!login.getEmail().equals(potentialLogin.get().getEmail()) && !login.getPasswordHash().equals("DUMMY"))               //ne | neq
        {
            potentialLogin.get().setEmail(login.getEmail());
            potentialLogin.get().setPasswordHash(login.getPasswordHash());
            userSecurityService.hashPasswordOfLogin(potentialLogin.get());
        } else if (!login.getEmail().equals(potentialLogin.get().getEmail()) && login.getPasswordHash().equals("DUMMY")) {      //neq | eq
            potentialLogin.get().setEmail(login.getEmail());
        } else if (login.getEmail().equals(potentialLogin.get().getEmail()) && !login.getPasswordHash().equals("DUMMY")) {      //eq  | neq
            potentialLogin.get().setPasswordHash(login.getPasswordHash());
            userSecurityService.hashPasswordOfLogin(potentialLogin.get());
        } else  {                                                                                                                //eq  | eq
            throw new EmailAlreadyExistsException("Email currently exists");
        }
    }
}
