package com.mms.dto;

import com.mms.model.ProductEntity;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {

    private int id;
    private String nameOfCategory;
    private Set<ProductEntity> products = new HashSet<>();

}
