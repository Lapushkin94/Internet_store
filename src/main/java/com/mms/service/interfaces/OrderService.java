package com.mms.service.interfaces;

import com.mms.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    List<OrderDTO> getAllOrders(int page);
    void addOrder(OrderDTO orderDTO);
    void deleteOrder(OrderDTO orderDTO);
    void editOrder(OrderDTO orderDTO);
    OrderDTO getOrder(int id);
    int getOrderCount();


}
