package com.mms.service.interfaces;


import com.mms.dto.OrderStatusDTO;

public interface OrderStatusService {

    OrderStatusDTO getOpenedStatus();
    OrderStatusDTO getInProcessStatus();
    OrderStatusDTO getClosedStatus();

}
