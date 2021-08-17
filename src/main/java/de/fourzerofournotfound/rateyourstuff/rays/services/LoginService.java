package de.fourzerofournotfound.rateyourstuff.rays.services;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.LoginDto;
import de.fourzerofournotfound.rateyourstuff.rays.dtos.UserDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.Login;
import de.fourzerofournotfound.rateyourstuff.rays.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service("ls")
public class LoginService {
    private final ModelMapper modelMapper;

    LoginService(ModelMapper mapper) {modelMapper = mapper;}


    public LoginDto convertToDto(Login login) {
        return modelMapper.map(login, LoginDto.class);
    }
}
