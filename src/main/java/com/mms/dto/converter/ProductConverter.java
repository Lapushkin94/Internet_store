package com.mms.dto.converter;

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
                .productDetails(productDTO.getProductDetails())
                .category(productDTO.getCategory())
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
                .productDetails(productEntity.getProductDetails())
                .category(productEntity.getCategory())
                .build();
    }

}
