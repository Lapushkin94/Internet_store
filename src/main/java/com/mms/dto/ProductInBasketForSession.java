package com.mms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductInBasketForSession {

    private String productName;
    private int quantity;
    private int price;

    private String alternative_name;
    private String brandName;
    private String color;
    private int weight;
    private String country;
    private String description;

}
