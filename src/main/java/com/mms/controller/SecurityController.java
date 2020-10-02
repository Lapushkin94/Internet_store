package com.mms.controller;


import com.mms.dto.ClientAddressDTO;
import com.mms.dto.ClientDTO;
import com.mms.dto.converterDTO.ClientAddressConverter;
import com.mms.dto.converterDTO.RoleConverter;
import com.mms.service.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController {

    private ClientService clientService;

    private int clientListPage;

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
            clientDTO.setRole(RoleConverter.toEntity(clientService.getRoleByRoleName("ROLE_CLIENT")));
            clientService.addClient(clientDTO);
            return "redirect:/signIn";
        }
        catch (Exception exc) {
            return "signUpPage";
        }
    }

    @GetMapping("/clientControl")
    public ModelAndView getClientList(@RequestParam(defaultValue = "1") int clientListPage) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("clientControlPage");

        this.clientListPage = clientListPage;
        int clientCount = clientService.getClientCount("ROLE_CLIENT");
        modelAndView.addObject("clientListPage", clientListPage);
        modelAndView.addObject("clientList", clientService.getAllClients(clientListPage, "ROLE_CLIENT"));
        modelAndView.addObject("clientCount", clientCount);
        modelAndView.addObject("clientPagesCount", (clientCount + 9) / 10);

        return modelAndView;
    }

    @GetMapping(value = "/clientControl/clientAddress/{id}")
    public ModelAndView getClientAddress(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("clientAddress");

        modelAndView.addObject("clientListPage", clientListPage);
        modelAndView.addObject("client", clientService.getClient(id));

        return modelAndView;
    }


    @GetMapping(value = "/clientControl/deleteClient/{id}")
    public ModelAndView deleteClient(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/catalog/?clientListPage=" + this.clientListPage);

        clientService.deleteClient(clientService.getClient(id));

        return modelAndView;
    }

    @GetMapping(value = "/myProfile")
    public ModelAndView getMyProfile(@AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("myProfilePage");

        ClientDTO clientDTO = clientService.getClientByEmail(user.getUsername());
        modelAndView.addObject("client", clientDTO);

        return modelAndView;
    }

    @GetMapping("/orderList")
    public String getOrderList() {
        return "orderListPage";
    }

}
