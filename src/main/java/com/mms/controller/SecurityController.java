package com.mms.controller;


import com.mms.dto.ClientDTO;
import com.mms.dto.converterDTO.RoleConverter;
import com.mms.service.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController {

    private ClientService clientService;

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/signIn")
    public String showMySignInPage() {
        return "signInPage";
    }

    @GetMapping("/accessDenied")
    public String showAccessDenied() {
        return "accessDenied";
    }

    @GetMapping("/logout")
    public String showLogoutPage() {
        return "logoutPage";
    }

    @GetMapping("/signUpPage")
    public String getSignUpPage() {
        return "signUpPage";
    }

    @PostMapping("/signUp")
    public ModelAndView addClient(@ModelAttribute ClientDTO clientDTO) {
        ModelAndView modelAndView = new ModelAndView();
        clientDTO.setRole(RoleConverter.toEntity(clientService.getRoleByRoleName("client")));
        clientService.addClient(clientDTO);
        modelAndView.setViewName("signUpPage");
        return modelAndView;
    }


}
