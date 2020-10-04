package com.mms.controller;

import com.mms.dto.*;
import com.mms.dto.converterDTO.*;
import com.mms.model.OrderEntity;
import com.mms.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping(value = "/order")
public class OrderController {

    private OrderService orderService;
    private ProductInBascetService productInBascetService;
    private OrderStatusService orderStatusService;
    private ClientService clientService;

    private int orderListPage;
    private int productInBascetListPage;

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setProductInBascetService(ProductInBascetService productInBascetService) {
        this.productInBascetService = productInBascetService;
    }

    @Autowired
    public void setOrderStatusService(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @GetMapping
    public ModelAndView getBascet(@RequestParam(defaultValue = "1") int productInBascetListPage) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("order/checkBascetBeforeRegistration");
        this.productInBascetListPage = productInBascetListPage;
        int productsInBascetCount = productInBascetService.getProductCount();
        modelAndView.addObject("productInBascetListPage", productInBascetListPage);
        modelAndView.addObject("productInBascetList", productInBascetService.getAllProductsInBascet(productInBascetListPage));
        modelAndView.addObject("productsInBascetCount", productsInBascetCount);
        modelAndView.addObject("productInBascetPagesCount", (productsInBascetCount + 9) / 10);
        return modelAndView;
    }


    @GetMapping(value = "/orderRegistrationPage")
    public ModelAndView getOrderRegistrationPage(@AuthenticationPrincipal User user) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("order/orderRegistrationPage");
        modelAndView.addObject("client", clientService.getClientByEmail(user.getUsername()));

        return modelAndView;
    }


    @PostMapping(value = "/confirmation")
    public String doOrderRegistration(@ModelAttribute("order") OrderDTO orderDTO,
                                      @ModelAttribute("clientAddress") ClientAddressDTO clientAddressDTO,
                                      @AuthenticationPrincipal User user) {

        ClientDTO clientDTO = clientService.getClientByEmail(user.getUsername());
        clientDTO.setClientAddress(ClientAddressConverter.toEntity(clientAddressDTO));
        clientService.editClient(clientDTO);

        // needs refactoring
        orderDTO.setId(0);
        orderDTO.setClient(ClientConverter.toEntity(clientDTO));
        orderDTO.setOrderStatus(OrderStatusConverter.toEntity(orderStatusService.getOpenedStatus()));
        orderDTO.setDate(dateFormat.format(new Date()));

        int orderId = orderService.addOrderAndReturnId(orderDTO);

        // needs refactoring
        String result = orderService.calculateProductNumberInStoreAlsoCopyProductInfoToTheHistoryTableAndResetProductBascet(productInBascetService.getAllProductsInBascetWithoutPages(), orderId);

        if (!result.equals("completedSuccessfully")) {
            return "order/notEnoughProducts";
        }

        if (orderDTO.getPayStatus().equals("Card online")) {
            return "order/payment";
        }

        return "order/congratulationsPage";
    }

    @GetMapping(value = "/successfulPayment")
    public ModelAndView getSuccessfulPaymentPage(@AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("order/successfulPayment");
        modelAndView.addObject("clientName", clientService.getClientByEmail(user.getUsername()).getName());
        return modelAndView;
    }

}
