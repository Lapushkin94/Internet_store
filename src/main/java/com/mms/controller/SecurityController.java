package com.mms.controller;


import com.mms.dto.ClientAddressDTO;
import com.mms.dto.ClientDTO;
import com.mms.dto.converterDTO.ClientAddressConverter;
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
    public String getAccessDeniedPage() {
        return "accessDenied";
    }

    @GetMapping("/logout")
    public String getLogoutPage() {
        return "logoutPage";
    }

    @GetMapping("/signUpPage")
    public String getSignUpPage() {
        return "signUpPage";
    }

    @PostMapping("/signUp")
    public String addClient(@ModelAttribute ClientDTO clientDTO,
                            @ModelAttribute ClientAddressDTO clientAddressDTO) {
        try {
            clientDTO.setClientAddress(ClientAddressConverter.toEntity(clientAddressDTO));
            clientDTO.setRole(RoleConverter.toEntity(clientService.getRoleByRoleName("ROLE_USER")));
            clientService.addClient(clientDTO);
            return "redirect:/signIn";
        }
        catch (Exception exc) {
            return "signUpPage";
        }
    }


}
