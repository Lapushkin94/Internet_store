package com.mms.dto.converterDTO;

import com.mms.dto.ProductDetailsDTO;
import com.mms.model.ProductDetailsEntity;

public class ProductDetailsConverter {

    public static ProductDetailsEntity toEntity(ProductDetailsDTO productDetailsDTO) {
        return ProductDetailsEntity.builder()
                .id(productDetailsDTO.getId())
                .color(productDetailsDTO.getColor())
                .weight(productDetailsDTO.getWeight())
                .country(productDetailsDTO.getCountry())
                .description(productDetailsDTO.getDescription())
                .build();
    }

    public static ProductDetailsDTO toDto(ProductDetailsEntity productDetailsEntity) {
        return ProductDetailsDTO.builder()
                .id(productDetailsEntity.getId())
                .color(productDetailsEntity.getColor())
                .weight(productDetailsEntity.getWeight())
                .country(productDetailsEntity.getCountry())
                .description(productDetailsEntity.getDescription())
                .build();
    }

}
