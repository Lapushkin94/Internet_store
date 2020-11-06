package com.mms.repository.interfaces;

import com.mms.model.OrderStatusEntity;

import java.util.List;

public interface OrderStatusRepository {

    OrderStatusEntity findOpenedStatus();

    OrderStatusEntity findInProcessStatus();

    OrderStatusEntity findClosedStatus();

    List<OrderStatusEntity> findAllOrderStatus();

    OrderStatusEntity getOrderStatusByName(String statusName);

}
