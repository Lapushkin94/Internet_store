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

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(value = "/order")
public class OrderController {

    private OrderService orderService;
    private ProductInBascetService productInBascetService;
    private OrderStatusService orderStatusService;
    private ProductService productService;

    private int orderListPage;
    private int productInBascetListPage;


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
    public ModelAndView bascet(@RequestParam(defaultValue = "1") int productInBascetListPage) {
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
    public ModelAndView getOrderRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView();

        // Проверка пользователя на наличие регистрации
        // В зависимости от этого будет разный вывод страницы регистрации заказа
        // Либо заполнять все поля и не сохранять заказ,
        // Либо вывести заполненные данные клиента + адрес с возможностью изменить адрес

        modelAndView.setViewName("orderRegistrationPage");
        return modelAndView;
    }


    @PostMapping(value = "/confirmation")
    public ModelAndView doOrderRegistration(@ModelAttribute("order") OrderDTO orderDTO) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("congratilationsPage");

        // Если зарегистрирован, то добавить клиенту в сет заказов текущий

        // Далее необх. удалить из каталога товары корзины

//        ProductDTO productDTO;
//
//        List<ProductInBascetDTO> productInBascetDTOList = productInBascetService.getAllProductsInBascetWithoutPages();

//        for (ProductInBascetDTO prod : productInBascetDTOList) {
//
//
//            // заменить на метод setOrderToProductInBascetList
//            // Каждому продукту из корзины назначается номер заказа
//            prod.setOrder(OrderConverter.toEntity(orderDTO));
//            productInBascetService.editProduct(prod);
//
//            productDTO = ProductConverter.toDto(prod.getProduct());

            // сравнивается кол-во товаров в корзине и кол-во товаров в магазине, проверка возможности покупки
            // + отнимает купленное количество из имеющегося в каталоге

//            if ((prod.getProduct().getQuantityInStore() - prod.getQuantity()) < 0) {
//                modelAndView.setViewName("numberOfProductExceptionPage");
//                modelAndView.addObject("shortageProduct", prod);
//                return modelAndView;
//            }

            // Сверху и снизу аналогичные варианты, мб try catch лучше

            // Стоит сделать в методе сервиса order
//            try {
//                productDTO.setQuantityInStore(prod.getProduct().getQuantityInStore() - prod.getQuantity());
//            }
//            catch (Exception exc) {
//                modelAndView.setViewName("numberOfProductExceptionPage");
//                modelAndView.addObject("shortageProduct", prod);
//                return modelAndView;
//            }
//
//            productService.editProduct(productDTO);
//        }


        orderDTO.setOrderStatus(OrderStatusConverter.toEntity(orderStatusService.getOpenedStatus()));
        Date date = new Date();
        orderDTO.setDate(date.toString());
        orderService.addOrder(orderDTO);

        // добавить метод, который будет копировать данные всех productInBascet в новую таблицу (таблицу создать)
        orderService.calculateProductNumberInStoreAlsoCopyProductInfoToTheHistoryTableAndResetProductBascet(productInBascetService.getAllProductsInBascetWithoutPages(), orderDTO.getId());

        return modelAndView;
    }

//
//    @GetMapping(value = "/fullOrderList")
//    public ModelAndView orderMainPage(@RequestParam (defaultValue = "1") int orderListPage) {
//        this.orderListPage = orderListPage;
//        int ordersCount = orderService.getOrderCount();
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("orderPage");
//        modelAndView.addObject("orderListPage", orderListPage);
//        modelAndView.addObject("orderList", orderService.getAllOrders(orderListPage));
//        modelAndView.addObject("orderCount", ordersCount);
//        modelAndView.addObject("orderPagesCount", (ordersCount + 9) / 10);
//        return modelAndView;
//    }


}
