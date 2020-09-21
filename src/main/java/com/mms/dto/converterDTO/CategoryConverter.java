package com.mms.dto.converterDTO;

import com.mms.dto.CategoryDTO;
import com.mms.model.CategoryEntity;

public class CategoryConverter {

    public static CategoryEntity toEntity(CategoryDTO categoryDTO) {
        return CategoryEntity.builder()
                .id(categoryDTO.getId())
                .nameOfCategory(categoryDTO.getNameOfCategory())
                .products(categoryDTO.getProducts())
                .build();
    }

    public static CategoryDTO toDto(CategoryEntity categoryEntity) {
        return CategoryDTO.builder()
                .id(categoryEntity.getId())
                .nameOfCategory(categoryEntity.getNameOfCategory())
                .products(categoryEntity.getProducts())
                .build();
    }

}
