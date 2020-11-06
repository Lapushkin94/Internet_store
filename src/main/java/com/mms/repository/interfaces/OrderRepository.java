package com.mms.repository.interfaces;

import com.mms.model.OrderEntity;
import com.mms.model.OrderedProductForHistoryEntity;

import java.util.List;

public interface OrderRepository {

    List<OrderEntity> findAllOrders(int page);

    void saveOrder(OrderEntity orderEntity);

    void deleteOrder(OrderEntity orderEntity);

    void updateOrder(OrderEntity orderEntity);

    OrderEntity findOrderById(int id);

    int getOrderCount();

    int saveOrderAndReturnId(OrderEntity orderEntity);

    int getOrderCountByClientId(int clientId);

    int getProductsCountByOrdersId(int orderId);

    List<OrderedProductForHistoryEntity> findOrdersProductHistoryByOrderId(int orderId, int orderHistoryPage);

    List<OrderedProductForHistoryEntity> findOrdersProductHistoryByOrderIdWithoutPages(int orderId);

    List<OrderEntity> findAllOrdersWithoutPages();

    List<OrderEntity> findListOrdersByNumberOfDays(String currentDateMinusNumberOfDays);

}
