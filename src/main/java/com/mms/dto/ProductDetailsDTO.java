package com.mms.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ProductDetailsDTO {

    private int id;
    private String color;
    private int weight;
    private String country;
    private String description;


}
