package com.mms.controller;

import com.mms.dto.*;
import com.mms.dto.converterDTO.*;
import com.mms.service.interfaces.OrderService;
import com.mms.service.interfaces.OrderStatusService;
import com.mms.service.interfaces.ProductInBascetService;
import com.mms.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ProductService productService;

    private int orderListPage;
    private int productInBascetListPage;

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");


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

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ModelAndView getBascet(@RequestParam(defaultValue = "1") int productInBascetListPage) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("checkBascetBeforeRegistration");
        this.productInBascetListPage = productInBascetListPage;
        int productsInBascetCount = productInBascetService.getProductCount();
        modelAndView.addObject("productInBascetListPage", productInBascetListPage);
        modelAndView.addObject("productInBascetList", productInBascetService.getAllProductsInBascet(productInBascetListPage));
        modelAndView.addObject("productsInBascetCount", productsInBascetCount);
        modelAndView.addObject("productInBascetPagesCount", (productsInBascetCount + 9) / 10);
        return modelAndView;
    }


    @GetMapping(value = "/orderRegistrationPage")
    public String getOrderRegistrationPage() {

        // Проверка пользователя на наличие регистрации
        // В зависимости от этого будет разный вывод страницы регистрации заказа
        // Либо заполнять все поля и не сохранять заказ,
        // Либо вывести заполненные данные клиента + адрес с возможностью изменить адрес

        return "orderRegistrationPage";
    }


    @PostMapping(value = "/confirmation")
    public ModelAndView doOrderRegistration(@ModelAttribute("order") OrderDTO orderDTO) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("congratilationsPage");

        // Если зарегистрирован, то добавить клиенту в сет заказов текущий

        orderDTO.setOrderStatus(OrderStatusConverter.toEntity(orderStatusService.getOpenedStatus()));

        orderDTO.setDate(dateFormat.format(new Date()));
        int orderId = orderService.addOrderAndReturnId(orderDTO);

        // метод проверяет заказанное количество продуктов с количеством в каталоге + удаляет купленное число, если можно
        // копирует данные корзины в таблицу истории и добавляет id заказа
        // обнуляет корзину
        orderService.calculateProductNumberInStoreAlsoCopyProductInfoToTheHistoryTableAndResetProductBascet(productInBascetService.getAllProductsInBascetWithoutPages(), orderId);

        return modelAndView;
    }


}
