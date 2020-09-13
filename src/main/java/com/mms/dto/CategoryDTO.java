package com.mms.dto;

import com.mms.model.ProductEntity;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class CategoryDTO {

    private int id;
    private String name;
    private Set<ProductEntity> products = new HashSet<>();

}
