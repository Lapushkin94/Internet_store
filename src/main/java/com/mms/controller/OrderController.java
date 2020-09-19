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


    @GetMapping(value = "/registrationPage")
    public ModelAndView registrationPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registrationPage");
        return modelAndView;
    }


    @PostMapping(value = "/confirmation")
    public ModelAndView getOrderPage(@ModelAttribute("order") OrderDTO orderDTO) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("congratilationsPage");

        // Проверка пользователя на наличие регистрации
        // Если есть, то привязать его к заказу, чтобы потом можно было выводить историю заказов по клиенту

        ProductDTO productDTO;

        List<ProductInBascetDTO> productInBascetDTOList = productInBascetService.getAllProductsInBascetWithoutPages();

        for (ProductInBascetDTO prod : productInBascetDTOList) {


            // заменить на метод setOrderToProductInBascetList
            // Каждому продукту из корзины назначается номер заказа
            prod.setOrder(OrderConverter.toEntity(orderDTO));
            productInBascetService.editProduct(prod);

            productDTO = ProductConverter.toDto(prod.getProduct());

            // сравнивается кол-во товаров в корзине и кол-во товаров в магазине, проверка возможности покупки
            // + отнимает купленное количество из имеющегося в каталоге

//            if ((prod.getProduct().getQuantityInStore() - prod.getQuantity()) < 0) {
//                modelAndView.setViewName("numberOfProductExceptionPage");
//                modelAndView.addObject("shortageProduct", prod);
//                return modelAndView;
//            }

            // Сверху и снизу аналогичные варианты, мб try catch лучше

            // Стоит сделать в методе сервиса, но обращение идет к разным БД
            try {
                productDTO.setQuantityInStore(prod.getProduct().getQuantityInStore() - prod.getQuantity());
            }
            catch (Exception exc) {
                modelAndView.setViewName("numberOfProductExceptionPage");
                modelAndView.addObject("shortageProduct", prod);
                return modelAndView;
            }

            productService.editProduct(productDTO);
        }


        orderDTO.setOrderStatus(OrderStatusConverter.toEntity(orderStatusService.getOpenedStatus()));
        Date date = new Date();
        orderDTO.setDate(date.toString());

        orderService.addOrder(orderDTO);

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
