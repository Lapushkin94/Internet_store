package com.mms.dto.converterDTO;

import com.mms.dto.OrderedProductForHistoryDTO;
import com.mms.model.OrderedProductForHistoryEntity;

public class OrderedProductForHistoryConverter {

    public static OrderedProductForHistoryEntity toEntity(OrderedProductForHistoryDTO orderedProductForHistoryDTO) {
        return OrderedProductForHistoryEntity.builder()
                .id(orderedProductForHistoryDTO.getId())
                .name(orderedProductForHistoryDTO.getName())
                .alternative_name(orderedProductForHistoryDTO.getAlternative_name())
                .brandName(orderedProductForHistoryDTO.getBrandName())
                .price(orderedProductForHistoryDTO.getPrice())
                .color(orderedProductForHistoryDTO.getColor())
                .weight(orderedProductForHistoryDTO.getWeight())
                .country(orderedProductForHistoryDTO.getCountry())
                .description(orderedProductForHistoryDTO.getDescription())
                .quantity(orderedProductForHistoryDTO.getQuantity())
                .orderInHistory(orderedProductForHistoryDTO.getOrderInHistory())
                .build();
    }


    public static OrderedProductForHistoryDTO toDto(OrderedProductForHistoryEntity orderedProductForHistoryEntity) {
        return OrderedProductForHistoryDTO.builder()
                .id(orderedProductForHistoryEntity.getId())
                .name(orderedProductForHistoryEntity.getName())
                .alternative_name(orderedProductForHistoryEntity.getAlternative_name())
                .brandName(orderedProductForHistoryEntity.getBrandName())
                .price(orderedProductForHistoryEntity.getPrice())
                .color(orderedProductForHistoryEntity.getColor())
                .weight(orderedProductForHistoryEntity.getWeight())
                .country(orderedProductForHistoryEntity.getCountry())
                .description(orderedProductForHistoryEntity.getDescription())
                .quantity(orderedProductForHistoryEntity.getQuantity())
                .orderInHistory(orderedProductForHistoryEntity.getOrderInHistory())
                .build();
    }

}
