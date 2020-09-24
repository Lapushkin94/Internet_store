package com.mms.service.interfaces;

import com.mms.dto.OrderedProductForHistoryDTO;

import java.util.List;

public interface OrderedProductForHistoryService {

    List<OrderedProductForHistoryDTO> getAllProductsWithPages(int page);
    List<OrderedProductForHistoryDTO> getAllProductsWithoutPages();
    void addProduct(OrderedProductForHistoryDTO orderedProductForHistoryDTO);
    int getProductCount();

}
