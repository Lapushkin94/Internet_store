package com.mms.dto.converterDTO;

import com.mms.dto.OrderDTO;
import com.mms.model.OrderEntity;

public class OrderConverter {

    public static OrderEntity toEntity(OrderDTO orderDTO) {
        return OrderEntity.builder()
                .id(orderDTO.getId())
                .date(orderDTO.getDate())
                .payStatus(orderDTO.getPayStatus())
                .comment(orderDTO.getComment())
                .orderStatus(orderDTO.getOrderStatus())
                .client(orderDTO.getClient())
                .productInBascets(orderDTO.getProductInBascets())
                .build();
    }

    public static OrderDTO toDto(OrderEntity orderEntity) {
        return OrderDTO.builder()
                .id(orderEntity.getId())
                .date(orderEntity.getDate())
                .payStatus(orderEntity.getPayStatus())
                .comment(orderEntity.getComment())
                .orderStatus(orderEntity.getOrderStatus())
                .client(orderEntity.getClient())
                .productInBascets(orderEntity.getProductInBascets())
                .build();
    }

}
