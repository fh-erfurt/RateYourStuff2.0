package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.models.Network;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.NetworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TestController {
    @Autowired
    NetworkRepository repository;
//test

    @GetMapping("/test")
    public String dbTestForm(Model model) {
        List<Network> networks = repository.findAll();
        model.addAttribute("networks", networks);
        model.addAttribute("newNetwork", new Network(""));

        return "test";
    }

    @PostMapping("/test")
    public ModelAndView dbTestPost(@ModelAttribute(value="newNetwork") Network network, Model model) {
        repository.save(network);
        return new ModelAndView("redirect:/test");
    }
}
