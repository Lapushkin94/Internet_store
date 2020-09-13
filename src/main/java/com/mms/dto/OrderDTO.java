package com.mms.dto;

import com.mms.model.ClientEntity;
import com.mms.model.OrderStatusEntity;
import com.mms.model.ProductInBascetEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class OrderDTO {

    private int id;
    private String date;
    private String payStatus;
    private String comment;
    private OrderStatusEntity orderStatus;
    private ClientEntity client;
    private Set<ProductInBascetEntity> productInBascets = new HashSet<>();

}
