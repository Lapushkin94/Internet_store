package com.mms.controller;

import com.mms.dto.*;
import com.mms.dto.converterDTO.*;
import com.mms.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;


@Controller
@RequestMapping(value = "/order")
public class OrderController {

    private static final Logger logger = Logger.getLogger(OrderConverter.class.getName());

    private OrderService orderService;
    private ProductInBascetService productInBascetService;
    private OrderStatusService orderStatusService;
    private ClientService clientService;
    private OrderedProductForHistoryService orderedProductForHistoryService;

    private int productInBascetListPage;

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");

    @Autowired
    public void setOrderedProductForHistoryService(OrderedProductForHistoryService orderedProductForHistoryService) {
        this.orderedProductForHistoryService = orderedProductForHistoryService;
    }

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

        logger.info("getting products count");
        int productsInBascetCount = productInBascetService.getProductCount();
        modelAndView.addObject("summPrice", productInBascetService.getSummPriceForAllProducts(productInBascetService.getAllProductsInBascetWithoutPages()));
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

        logger.info("getting client by email " + user.getUsername());
        ClientDTO clientDTO = clientService.getClientByEmail(user.getUsername());
        clientDTO.setClientAddress(ClientAddressConverter.toEntity(clientAddressDTO));
        clientDTO.setActive(true);
        logger.info("editing client " + clientDTO.getId());
        clientService.editClient(clientDTO);

        // needs refactoring
        orderDTO.setId(0);
        orderDTO.setClient(ClientConverter.toEntity(clientDTO));
        orderDTO.setOrderStatus(OrderStatusConverter.toEntity(orderStatusService.getOpenedStatus()));
        orderDTO.setDate(dateFormat.format(new Date()));

        logger.info("adding order " + orderDTO.getId());
        int orderId = orderService.addOrderAndReturnId(orderDTO);

        // needs refactoring
        logger.info("creating order + quantity calculating");
        String result = orderService.calculateProductNumberInStoreAlsoCopyProductInfoToTheHistoryTableAndResetProductBascet(
                productInBascetService.getAllProductsInBascetWithoutPages(), orderId);

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
