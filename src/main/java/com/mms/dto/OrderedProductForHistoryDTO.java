package com.mms.dto;


import com.mms.model.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderedProductForHistoryDTO {

    private int id;
    private String name;
    private String alternative_name;
    private String brandName;
    private int price;
    private String color;
    private int weight;
    private String country;
    private String description;
    private int quantity;
    private OrderEntity orderInHistory;

}
