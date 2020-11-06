package com.mms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.LinkedHashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketForSession {

    Map<Integer, ProductInBasketForSession> productsInBasket = new LinkedHashMap<>();

}
