package com.mms.service.implementations;

import com.mms.dto.ProductForStand;
import com.mms.service.interfaces.OrderedProductForHistoryService;
import com.mms.service.interfaces.ProductForStandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductsForStandServiceImpl  implements ProductForStandService {

    private OrderedProductForHistoryService orderedProductForHistoryService;

    @Autowired
    public void setOrderedProductForHistoryService(OrderedProductForHistoryService orderedProductForHistoryService) {
        this.orderedProductForHistoryService = orderedProductForHistoryService;
    }

    @Override
    public List<ProductForStand> getProductsForStandList() {

        Map<String, Integer> productsForStandMap = orderedProductForHistoryService.getTop10ProductsBySoldNumber();

        List<ProductForStand> productForStandList = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : productsForStandMap.entrySet()) {

            ProductForStand productForStand = new ProductForStand();
            productForStand.setName(entry.getKey());
            productForStand.setPrice(entry.getValue());

            productForStandList.add(productForStand);
        }

        return productForStandList;
    }


}
