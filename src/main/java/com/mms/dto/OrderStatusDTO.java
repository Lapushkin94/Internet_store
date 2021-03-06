package com.mms.dto;

import com.mms.model.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderStatusDTO {

    private int id;
    private String name;
    private Set<OrderEntity> orders = new HashSet<>();

}
