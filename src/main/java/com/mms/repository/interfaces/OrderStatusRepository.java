package com.mms.repository.interfaces;

import com.mms.model.OrderStatusEntity;

public interface OrderStatusRepository {

    OrderStatusEntity findOpenedStatus();
    OrderStatusEntity findInProcessStatus();
    OrderStatusEntity findClosedStatus();

}
