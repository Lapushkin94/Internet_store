package com.mms.controller;


import com.mms.dto.ClientAddressDTO;
import com.mms.dto.ClientDTO;
import com.mms.dto.OrderDTO;
import com.mms.dto.converterDTO.ClientAddressConverter;
import com.mms.dto.converterDTO.OrderStatusConverter;
import com.mms.dto.converterDTO.RoleConverter;
import com.mms.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

@Controller
public class SecurityController {

    private static final Logger logger = Logger.getLogger(SecurityController.class.getName());

    private ClientService clientService;
    private OrderService orderService;
    private OrderStatusService orderStatusService;
    private OrderedProductForHistoryService orderedProductForHistoryService;

    private int clientListPage;
    private int orderListPage;
    private int orderProductsListPage;

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");

    @Autowired
    public void setOrderedProductForHistoryService(OrderedProductForHistoryService orderedProductForHistoryService) {
        this.orderedProductForHistoryService = orderedProductForHistoryService;
    }

    @Autowired
    public void setOrderStatusService(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
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

        return "redirect:/catalog/resetFilterAfterLogout";
    }

    @GetMapping("/signUpPage")
    public ModelAndView getSignUpPage(@RequestParam(value = "passwordStatus", defaultValue = "0") int passwordStatus,
                                      @RequestParam(value = "emailStatus", defaultValue = "0") int emailStatus) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("security/signUpPage");

        modelAndView.addObject("emailStatus", emailStatus);
        modelAndView.addObject("passwordStatus", passwordStatus);

        return modelAndView;
    }

    @PostMapping("/signUp")
    public String addClient(@ModelAttribute ClientDTO clientDTO,
                            @ModelAttribute ClientAddressDTO clientAddressDTO,
                            @RequestParam("firstPassword") String firstPassword,
                            @RequestParam("secondPassword") String secondPassword) {

        if (!firstPassword.equals(secondPassword)) {
            logger.info("passwords not equals");
            return "redirect:/signUpPage/?passwordStatus=1";
        }

        clientDTO.setPassword(firstPassword);
        clientDTO.setClientAddress(ClientAddressConverter.toEntity(clientAddressDTO));
        clientDTO.setRole(RoleConverter.toEntity(clientService.getRoleByRoleName("ROLE_CLIENT")));

        String uniqEmailStatus = clientService.addClientAndReturnUniqEmailStatus(clientDTO);

        if (!uniqEmailStatus.equals("okStatus")) return uniqEmailStatus;

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
        modelAndView.setViewName("redirect:/clientControl/?clientListPage=" + this.clientListPage);

        clientService.deleteClient(clientService.getClient(id));

        return modelAndView;
    }


    @GetMapping("/orderList")
    public ModelAndView getOrderList(@RequestParam(defaultValue = "1") int orderListPage) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("order/orderListPage");

        this.orderListPage = orderListPage;
        int ordersCount = orderService.getOrderCount();

        modelAndView.addObject("orderListPage", orderListPage);
        modelAndView.addObject("orderList", orderService.getAllOrders(orderListPage));
        modelAndView.addObject("ordersCount", ordersCount);
        modelAndView.addObject("orderPagesCount", (ordersCount + 9) / 10);

        return modelAndView;
    }

    @GetMapping("/orderList/clientDetails/{id}")
    public ModelAndView getClientsProfile(@PathVariable("id") int id) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("client/clientProfilePage");

        modelAndView.addObject("orderListPage", orderListPage);
        modelAndView.addObject("client", clientService.getClient(id));

        return modelAndView;
    }

    @GetMapping("/orderList/orderDetails/{id}")
    public ModelAndView getOrderDetails(@PathVariable("id") int id,
                                        @RequestParam(defaultValue = "1") int orderProductsListPage) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("client/orderDetails");

        this.orderProductsListPage = orderProductsListPage;
        int orderProductsCount = orderService.getProductsCountByOrdersId(id);
        modelAndView.addObject("orderStatusList", orderStatusService.getAllOrderStatus());
        modelAndView.addObject("orderListPage", orderListPage);
        modelAndView.addObject("ordersId", id);
        modelAndView.addObject("orderStatus", OrderStatusConverter.toDto(orderService.getOrder(id).getOrderStatus()));
        modelAndView.addObject("orderProductsListPage", orderProductsListPage);
        modelAndView.addObject("orderProductsList", orderService.getOrdersProductHistoryByOrderId(id, orderProductsListPage));
        modelAndView.addObject("orderProductsCount", orderProductsCount);
        modelAndView.addObject("orderProductsPagesCount", (orderProductsCount + 9) / 10);

        return modelAndView;
    }

    @PostMapping("/orderList/orderDetails/changeOrderStatus")
    public ModelAndView setNewStatusForOrder(@RequestParam("orderStatusName") String statusName,
                                             @RequestParam("enterOrderId") int enterOrderId) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/orderList/orderDetails/" + enterOrderId + "?orderProductsListPage=" + this.orderProductsListPage);

        logger.info("setting new status " + statusName);
        OrderDTO orderDTO = orderService.getOrder(enterOrderId);
        orderDTO.setOrderStatus(OrderStatusConverter.toEntity(orderStatusService.getOrderStatusByName(statusName)));
        orderService.editOrder(orderDTO);

        return modelAndView;
    }

    @GetMapping(value = "/statistics")
    public ModelAndView getStatisticsPage() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("stats/statisticsPage");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        Date dateMinusOneDay = calendar.getTime();
        calendar.add(Calendar.DATE, -2);
        Date dateMinusThreeDays = calendar.getTime();
        calendar.add(Calendar.DATE, -4);
        Date dateMinusSevenDays = calendar.getTime();
        calendar.add(Calendar.DATE, -27);
        Date dateMinusMonth = calendar.getTime();

        modelAndView.addObject("topTenProducts", orderedProductForHistoryService.getTop10ProductsBySoldNumber());
        modelAndView.addObject("top10clientsByProfit", orderedProductForHistoryService.getTop10clientsByProfit());
        modelAndView.addObject("totalProfitOfTheDay", orderedProductForHistoryService.getTotalProfitByNumberOfDays(dateFormat.format(dateMinusOneDay)));
        modelAndView.addObject("totalProfitOfTheThreeDays", orderedProductForHistoryService.getTotalProfitByNumberOfDays(dateFormat.format(dateMinusThreeDays)));
        modelAndView.addObject("totalProfitOfTheWeek", orderedProductForHistoryService.getTotalProfitByNumberOfDays(dateFormat.format(dateMinusSevenDays)));
        modelAndView.addObject("totalProfitOfTheMonth", orderedProductForHistoryService.getTotalProfitByNumberOfDays(dateFormat.format(dateMinusMonth)));

        return modelAndView;
    }

}
