package com.mms.service.interfaces;

import com.mms.dto.OrderedProductForHistoryDTO;

import java.util.List;
import java.util.Map;

public interface OrderedProductForHistoryService {

    List<OrderedProductForHistoryDTO> getAllProductsWithPages(int page);

    List<OrderedProductForHistoryDTO> getAllProductsWithoutPages();

    void addProduct(OrderedProductForHistoryDTO orderedProductForHistoryDTO);

    int getProductCount();

    Map<String, Integer> getTop10ProductsBySoldNumber();

    Map<String, Integer> getTop10clientsByProfit();

    int getTotalProfitByNumberOfDays(String dateMinusNumberOfDays);

    Map<String, Integer> getTop10ProductsBySoldNumberOptimizedVersion();

    Map<String, Integer> getTop10clientsByProfitOptimizedVersion();

    int getTotalProfitByNumberOfDaysOptimizedVersion(String currentDateMinusNumberOfDays);

}
