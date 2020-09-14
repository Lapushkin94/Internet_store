package com.mms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetailsDTO {

    private int id;
    private String color;
    private int weight;
    private String country;
    private String description;


}
