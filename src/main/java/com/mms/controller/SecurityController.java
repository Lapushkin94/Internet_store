package com.mms.controller;


import com.mms.dto.ClientAddressDTO;
import com.mms.dto.ClientDTO;
import com.mms.dto.converterDTO.ClientAddressConverter;
import com.mms.dto.converterDTO.RoleConverter;
import com.mms.service.interfaces.ClientService;
import com.mms.service.interfaces.ProductInBascetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController {

    private ClientService clientService;
    private ProductInBascetService productInBascetService;

    private int clientListPage;

    @Autowired
    public void setProductInBascetService(ProductInBascetService productInBascetService) {
        this.productInBascetService = productInBascetService;
    }

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/signIn")
    public String showMySignInPage() {
        return "security/signInPage";
    }

    @GetMapping("/accessDenied")
    public String getAccessDeniedPage() {
        return "exceptions/accessDenied";
    }

    @GetMapping("/logoutSuccessPage")
    public String getLogoutPage() {
        productInBascetService.resetProductInBascetTable();
        return "security/logoutSuccessPage";
    }

    @GetMapping("/signUpPage")
    public ModelAndView getSignUpPage(@RequestParam(value = "passwordStatus", defaultValue = "0") String passwordStatus) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("security/signUpPage");

        modelAndView.addObject("passwordStatus", passwordStatus);

        return modelAndView;
    }

    @PostMapping("/signUp")
    public String addClient(@ModelAttribute ClientDTO clientDTO,
                            @ModelAttribute ClientAddressDTO clientAddressDTO,
                            @RequestParam("firstPassword") String firstPassword,
                            @RequestParam("secondPassword") String secondPassword) {

        if (!firstPassword.equals(secondPassword)) {
            return "redirect:/signUpPage?passwordStatus=1";
        }

        // Добавить @Valid или обработать bad request
            clientDTO.setPassword(firstPassword);
            clientDTO.setClientAddress(ClientAddressConverter.toEntity(clientAddressDTO));
            clientDTO.setRole(RoleConverter.toEntity(clientService.getRoleByRoleName("ROLE_CLIENT")));
            clientService.addClient(clientDTO);
            return "redirect:/signIn";
    }

    @GetMapping("/clientControl")
    public ModelAndView getClientList(@RequestParam(defaultValue = "1") int clientListPage) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("client/clientControlPage");

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
        modelAndView.setViewName("client/clientAddress");

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





    @GetMapping("/orderList")
    public String getOrderList() {
        return "order/orderListPage";
    }

}
