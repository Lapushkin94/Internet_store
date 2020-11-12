package com.mms.service.implementations;

import com.mms.dto.ProductForStand;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductsForStandServiceImplTest {

    @InjectMocks
    ProductsForStandServiceImpl productsForStandService;

    @Mock
    OrderedProductForHistoryServiceImpl orderedProductForHistoryService;

    @Test
    public void getProductsForStandList() {

        Map<String, Integer> mapToReturn = new LinkedHashMap<>();
        mapToReturn.put("first", 300);
        mapToReturn.put("second", 600);
        mapToReturn.put("third", 900);

        when(orderedProductForHistoryService.getTop10ProductsBySoldNumberOptimizedVersion()).thenReturn(mapToReturn);

        List<ProductForStand> result = productsForStandService.getProductsForStandList();

        List<ProductForStand> expectedResult = new ArrayList<>();

        ProductForStand firstProductForStand = new ProductForStand();
        firstProductForStand.setName("first");
        firstProductForStand.setPrice(300);
        expectedResult.add(firstProductForStand);

        ProductForStand secondProductForStand = new ProductForStand();
        secondProductForStand.setName("second");
        secondProductForStand.setPrice(600);
        expectedResult.add(secondProductForStand);

        ProductForStand thirdProductForStand = new ProductForStand();
        thirdProductForStand.setName("third");
        thirdProductForStand.setPrice(900);
        expectedResult.add(thirdProductForStand);

        Assert.assertEquals(expectedResult, result);
    }

}