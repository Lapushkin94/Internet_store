package com.mms.repository.interfaces;


import com.mms.model.OrderedProductForHistoryEntity;

import java.util.List;

public interface OrderedProductForHistoryRepository {

    List<OrderedProductForHistoryEntity> findAllProductsWithPages(int page);
    List<OrderedProductForHistoryEntity> findAllProductsWithoutPages();
    void saveProduct(OrderedProductForHistoryEntity orderedProductForHistoryEntity);
    int getProductCount();

}
