package com.mms.service.implementations;

import com.mms.repository.interfaces.OrderedProductForHistoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderedProductForHistoryServiceImplTest {

    @InjectMocks
    OrderedProductForHistoryServiceImpl orderedProductForHistoryService;

    @Mock
    OrderedProductForHistoryRepository orderedProductForHistoryRepository;

    @Test
    public void getTop10ProductsBySoldNumberOptimizedVersion() {

        Object[] firstArray = {"firstProduct", 1};
        Object[] secondArray = {"secondProduct", 2};
        Object[] thirdArray = {"thirdProduct", 3};
        List<Object[]> listFromRepo = Arrays.asList(firstArray, secondArray, thirdArray);

        when(orderedProductForHistoryRepository.getTop10ProductsByPurchaseNumber()).thenReturn(listFromRepo);

        Map<String, Integer> result = orderedProductForHistoryService.getTop10ProductsBySoldNumberOptimizedVersion();

        Map<String, Integer> expectedResult = new LinkedHashMap<>();
        expectedResult.put("firstProduct", 1);
        expectedResult.put("secondProduct", 2);
        expectedResult.put("thirdProduct", 3);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void getTop10clientsByProfitOptimizedVersion() {

        Object[] firstArray = {"firstClient", 100};
        Object[] secondArray = {"secondClient", 200};
        Object[] thirdArray = {"thirdClient", 300};
        List<Object[]> listFromRepo = Arrays.asList(firstArray, secondArray, thirdArray);

        when(orderedProductForHistoryRepository.getTop10ClientsByPurchase()).thenReturn(listFromRepo);

        Map<String, Integer> result = orderedProductForHistoryService.getTop10clientsByProfitOptimizedVersion();

        Map<String, Integer> expectedResult = new LinkedHashMap<>();
        expectedResult.put("firstClient", 100);
        expectedResult.put("secondClient", 200);
        expectedResult.put("thirdClient", 300);

        Assert.assertEquals(expectedResult, result);
    }
}