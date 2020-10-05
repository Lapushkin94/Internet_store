package com.mms.service.interfaces;


import com.mms.dto.OrderStatusDTO;

import java.util.List;

public interface OrderStatusService {

    OrderStatusDTO getOpenedStatus();
    OrderStatusDTO getInProcessStatus();
    OrderStatusDTO getClosedStatus();
    List<OrderStatusDTO> getAllOrderStatus();
    OrderStatusDTO getOrderStatusByName(String name);

}
