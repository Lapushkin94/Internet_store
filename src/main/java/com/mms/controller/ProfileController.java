package com.mms.controller;

import com.mms.dto.*;
import com.mms.dto.converterDTO.ClientAddressConverter;
import com.mms.dto.converterDTO.OrderConverter;
import com.mms.dto.converterDTO.OrderStatusConverter;
import com.mms.service.interfaces.ClientService;
import com.mms.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/myProfile")
public class ProfileController {

    private ClientService clientService;
    private PasswordEncoder passwordEncoder;
    private OrderService orderService;

    private int orderListPage;
    private int orderHistoryListPage;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ModelAndView getMyProfile(@AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("client/myProfilePage");

        modelAndView.addObject("client", clientService.getClientByEmail(user.getUsername()));

        return modelAndView;
    }

    @GetMapping(value = "/editProfile")
    public ModelAndView getProfileEditPage(@AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("client/profileEditPage");

        modelAndView.addObject("client", clientService.getClientByEmail(user.getUsername()));

        return modelAndView;
    }

    @PostMapping(value = "/editProfile")
    public ModelAndView editProfile(@AuthenticationPrincipal User user,
                                    @ModelAttribute ClientDTO clientDTO) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/myProfile");

        clientDTO.setClientAddress(clientService.getClientByEmail(user.getUsername()).getClientAddress());
        clientDTO.setRole(clientService.getClientByEmail(user.getUsername()).getRole());
        clientService.editClient(clientDTO);

        return modelAndView;
    }

    @GetMapping(value = "/editAddress")
    public ModelAndView getAddressEditPage(@AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("client/addressEditPage");

        modelAndView.addObject("client", clientService.getClientByEmail(user.getUsername()));

        return modelAndView;
    }

    @PostMapping(value = "/editAddress")
    public ModelAndView editAddress(@AuthenticationPrincipal User user,
                                    @ModelAttribute ClientAddressDTO clientAddressDTO) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/myProfile");

        ClientDTO clientDTO = clientService.getClientByEmail(user.getUsername());
        clientDTO.setClientAddress(ClientAddressConverter.toEntity(clientAddressDTO));
        clientDTO.setRole(clientService.getClientByEmail(user.getUsername()).getRole());
        clientService.editClient(clientDTO);

        return modelAndView;
    }

    @GetMapping(value = "/editPassword")
    public ModelAndView getPasswordEditPageWithNoErrors(@RequestParam(defaultValue = "0") int error) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("client/passwordEditPage");
        modelAndView.addObject("error", error);
        return modelAndView;
    }

    @PostMapping(value = "/editPassword")
    public String editPassword(@RequestParam(name = "usersPassword") String usersPassword,
                               @RequestParam(name = "firstNewPassword") String firstNewPassword,
                               @RequestParam(name = "secondNewPassword") String secondNewPassword) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/myProfile");

        if (!firstNewPassword.equals(secondNewPassword)) {
            return "redirect:/myProfile/editPassword/?error=1";
        }

        ClientDTO clientDTO = clientService.getClientByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        if (!passwordEncoder.matches(usersPassword, clientDTO.getPassword())) {
            return "redirect:/myProfile/editPassword/?error=2";
        }

        clientDTO.setPassword(passwordEncoder.encode(firstNewPassword));
        clientService.editClient(clientDTO);

        return "redirect:/myProfile";
    }

    @GetMapping(value = "/myOrders")
    public ModelAndView getMyOrders(@AuthenticationPrincipal User user,
                                    @RequestParam(defaultValue = "1") int orderListPage) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("client/myOrders");

        this.orderListPage = orderListPage;
        int ordersCount = orderService.getOrderCountByClientId(clientService.getClientByEmail(user.getUsername()).getId());
        modelAndView.addObject("orderListPage", orderListPage);
        modelAndView.addObject("orderListByClientId", clientService.getOrderListByClientId(clientService.getClientByEmail(user.getUsername()).getId(), orderListPage));
        modelAndView.addObject("ordersCount", ordersCount);
        modelAndView.addObject("orderPagesCount", (ordersCount + 9) / 10);

        return modelAndView;
    }

    @GetMapping(value = "/myOrders/checkOrdersProducts/{id}")
    public ModelAndView getOrdersProductList(@PathVariable("id") int id,
                                             @RequestParam(defaultValue = "1") int orderHistoryListPage) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("client/showOrdersProductsPage");

        this.orderHistoryListPage = orderHistoryListPage;
        int orderHistoryCount = orderService.getProductsCountByOrdersId(id);

        modelAndView.addObject("orderListPage", orderListPage);
        modelAndView.addObject("ordersId", id);
        modelAndView.addObject("orderStatus", OrderStatusConverter.toDto(orderService.getOrder(id).getOrderStatus()));
        modelAndView.addObject("orderHistoryListPage", orderHistoryListPage);
        modelAndView.addObject("ordersProductList", orderService.getOrdersProductHistoryByOrderId(id, orderHistoryListPage));
        modelAndView.addObject("orderHistoryCount", orderHistoryCount);
        modelAndView.addObject("orderHistoryPagesCount", (orderHistoryCount + 9) / 10);

        return modelAndView;
    }

}
