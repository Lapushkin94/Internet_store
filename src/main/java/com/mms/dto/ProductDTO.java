package com.mms.dto;

import com.mms.model.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductDTO {

    private int id;
    private String name;
    private String alternative_name;
    private String brandName;
    private int price;
    private CategoryEntity category;
    private int quantityInStore;
    private String color;
    private int weight;
    private String country;
    private String description;

}
