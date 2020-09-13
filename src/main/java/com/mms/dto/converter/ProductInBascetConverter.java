package com.mms.dto.converter;

import com.mms.dto.ProductInBascetDTO;
import com.mms.model.ProductInBascetEntity;

public class ProductInBascetConverter {

    public static ProductInBascetEntity toEntity(ProductInBascetDTO productInBascetDTO) {
        return ProductInBascetEntity.builder()
                .id(productInBascetDTO.getId())
                .quantity(productInBascetDTO.getQuantity())
                .product(productInBascetDTO.getProduct())
                .order(productInBascetDTO.getOrder())
                .build();
    }

    public static ProductInBascetDTO toDto(ProductInBascetEntity productInBascetEntity) {
        return ProductInBascetDTO.builder()
                .id(productInBascetEntity.getId())
                .quantity(productInBascetEntity.getQuantity())
                .product(productInBascetEntity.getProduct())
                .order(productInBascetEntity.getOrder())
                .build();
    }

}
