package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.models.Login;
import de.fourzerofournotfound.rateyourstuff.rays.models.User;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.mindrot.jbcrypt.BCrypt;

@Controller
public class SignUpController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/signup")
    public String UserInitialization(Model model)
    {
        model.addAttribute("newUser", new User());
        //model.addAttribute("wa", "Whats Up?");  //This message will be send from the controller to the view!

        return "signup";
    }

    @PostMapping("/signup")
    public ModelAndView storeUser(@ModelAttribute(value = "newUser") User user, Login login, Model model)
    {
        user.getLogin().setIsEnabled(false);

        String passwordSalt = BCrypt.gensalt();
        user.getLogin().setPasswordHash(BCrypt.hashpw(login.getPasswordHash(), passwordSalt));

        //TODO: role and loginRole -/- Structure?

        userRepository.save(user);
        return new ModelAndView("redirect:/greeting");
    }

    //TODO: Methods

    //TODO: Update User

    //TODO: Update Password
    //TODO: Reset Password


}
