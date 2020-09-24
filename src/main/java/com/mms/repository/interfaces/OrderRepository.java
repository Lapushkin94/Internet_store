package com.mms.repository.interfaces;

import com.mms.model.OrderEntity;

import java.util.List;

public interface OrderRepository {

    List<OrderEntity> findAllOrders(int page);
    void saveOrder(OrderEntity orderEntity);
    void deleteOrder(OrderEntity orderEntity);
    void updateOrder(OrderEntity orderEntity);
    OrderEntity findOrderById(int id);
    int getOrderCount();
    int saveOrderAndReturnId(OrderEntity orderEntity);

}
