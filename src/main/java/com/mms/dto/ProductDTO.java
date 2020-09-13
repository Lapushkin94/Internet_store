package com.mms.dto;

import com.mms.model.CategoryEntity;
import com.mms.model.ProductDetailsEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ProductDTO {

    private int id;
    private String name;
    private String alternative_name;
    private String brandName;
    private int price;
    private ProductDetailsEntity productDetails;
    private CategoryEntity category;

}
