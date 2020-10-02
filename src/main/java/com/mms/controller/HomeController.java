package com.mms.controller;

import com.mms.dto.ClientDTO;
import com.mms.service.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {

    private ClientService clientService;

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "/")
    public ModelAndView homePage(@AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        if (user != null) {
            modelAndView.addObject("clientName", clientService.getClientByEmail(user.getUsername()).getName());
        }
        return modelAndView;
    }

    @GetMapping(value = "/profile")
    public String myProfile() {
        return "profile";
    }

    @GetMapping(value = "/aboutUs")
    public String aboutUs() {
        return "aboutUs";
    }

    @GetMapping(value = "/contacts")
    public String contacts() {
        return "contacts";
    }

}
