package com.mms.dto.converter;

import com.mms.dto.CategoryDTO;
import com.mms.model.CategoryEntity;

public class CategoryConverter {

    public static CategoryEntity toEntity(CategoryDTO categoryDTO) {
        return CategoryEntity.builder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .products(categoryDTO.getProducts())
                .build();
    }

    public static CategoryDTO toDto(CategoryEntity categoryEntity) {
        return CategoryDTO.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .products(categoryEntity.getProducts())
                .build();
    }

}
