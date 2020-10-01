package com.mms.dto.converterDTO;

import com.mms.dto.ProductDTO;
import com.mms.model.ProductEntity;

public class ProductConverter {

    public static ProductEntity toEntity(ProductDTO productDTO) {
        return ProductEntity.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .alternative_name(productDTO.getAlternative_name())
                .brandName(productDTO.getBrandName())
                .price(productDTO.getPrice())
                .quantityInStore(productDTO.getQuantityInStore())
                .category(productDTO.getCategory())
                .color((productDTO.getColor()))
                .weight(productDTO.getWeight())
                .country((productDTO.getCountry()))
                .description(productDTO.getDescription())
                .build();
    }

    public static ProductDTO toDto(ProductEntity productEntity) {
        return ProductDTO.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .alternative_name(productEntity.getAlternative_name())
                .brandName(productEntity.getBrandName())
                .price(productEntity.getPrice())
                .quantityInStore(productEntity.getQuantityInStore())
                .category(productEntity.getCategory())
                .color(productEntity.getColor())
                .weight(productEntity.getWeight())
                .country(productEntity.getCountry())
                .description(productEntity.getDescription())
                .build();
    }

}
