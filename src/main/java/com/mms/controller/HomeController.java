package com.mms.controller;

import com.mms.service.interfaces.ClientService;
import com.mms.service.interfaces.ProductForStandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class HomeController {

    private ClientService clientService;
    private ProductForStandService productForStandService;

    @Autowired
    public void setProductForStandService(ProductForStandService productForStandService) {
        this.productForStandService = productForStandService;
    }

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "/")
    public ModelAndView homePage(@AuthenticationPrincipal User user) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("basic/home");

        if (user != null) {
            modelAndView.addObject("clientName", clientService.getClientByEmail(user.getUsername()).getName());
        }
        return modelAndView;
    }

    @GetMapping(value = "/aboutUs")
    public String aboutUs() {
        return "basic/aboutUs";
    }

    @GetMapping(value = "/contacts")
    public String contacts() {
        return "basic/contacts";
    }

    @GetMapping(value = "/successContact")
    public String getSuccessContactPage() {
        return "client/successContact";
    }

    @GetMapping(value = "/initProductStand")
    public ModelAndView initProductStand() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");

        productForStandService.initMessage();

        return modelAndView;
    }

}
