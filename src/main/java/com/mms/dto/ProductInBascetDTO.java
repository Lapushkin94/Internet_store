package com.mms.dto;

import com.mms.model.OrderEntity;
import com.mms.model.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductInBascetDTO {

    private int id;
    private int quantity;
    private ProductEntity product;
    private OrderEntity order;

}
