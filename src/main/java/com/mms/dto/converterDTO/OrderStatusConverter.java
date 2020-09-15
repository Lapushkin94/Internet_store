package com.mms.dto.converterDTO;

import com.mms.dto.OrderStatusDTO;
import com.mms.model.OrderStatusEntity;

public class OrderStatusConverter {

    public static OrderStatusEntity toEntity(OrderStatusDTO orderStatusDTO) {
        return OrderStatusEntity.builder()
                .id(orderStatusDTO.getId())
                .name(orderStatusDTO.getName())
                .orders(orderStatusDTO.getOrders())
                .build();
    }

    public static OrderStatusDTO toDto(OrderStatusEntity orderStatusEntity) {
        return OrderStatusDTO.builder()
                .id(orderStatusEntity.getId())
                .name(orderStatusEntity.getName())
                .orders(orderStatusEntity.getOrders())
                .build();
    }

}
