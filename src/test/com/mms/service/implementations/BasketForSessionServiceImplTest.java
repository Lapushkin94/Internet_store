package com.mms.service.implementations;

import com.mms.dto.OrderedProductForHistoryDTO;
import com.mms.dto.ProductDTO;
import com.mms.dto.ProductInBasketForSession;
import com.mms.model.ProductEntity;
import com.mms.repository.interfaces.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BasketForSessionServiceImplTest {

    @InjectMocks
    private BasketForSessionServiceImpl basketForSessionService;

    @Mock
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void removeProductFromBasketByIdAndReturnActualBasket() {

        Map<Integer, ProductInBasketForSession> mapToChange = new HashMap<>();
        Map<Integer, ProductInBasketForSession> expectedResult = new HashMap<>();

        int firstId = 1;
        int secondId = 2;
        int idToDelete = 1;
        ProductInBasketForSession firstProductInBasketForSession = new ProductInBasketForSession();
        ProductInBasketForSession secondProductInBasketForSession = new ProductInBasketForSession();

        mapToChange.put(firstId, firstProductInBasketForSession);
        mapToChange.put(secondId, secondProductInBasketForSession);

        expectedResult.put(secondId, secondProductInBasketForSession);

        Map<Integer, ProductInBasketForSession> result = basketForSessionService
                .removeProductFromBasketByIdAndReturnActualBasket(mapToChange, idToDelete);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void calculateQuantityDifferenceToGetProductAndReturnCatalogParam_basketContainsSuchProductAndQuantityIsOk() {

        int productId = 1;
        int numberOfOrderedProducts = 5;

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productId);
        productDTO.setQuantityInStore(10);

        when(productService.getProduct(productId)).thenReturn(productDTO);

        Map<Integer, ProductInBasketForSession> inputMap = new HashMap<>();
        ProductInBasketForSession inputProductInBasketForSession = new ProductInBasketForSession();
        inputProductInBasketForSession.setQuantity(1);
        inputMap.put(productId, inputProductInBasketForSession);

        Map<String, Map<Integer, ProductInBasketForSession>> expectedResult = new HashMap<>();
        Map<Integer, ProductInBasketForSession> mapForExpectedResult = new HashMap<>();
        ProductInBasketForSession productInBasketForSessionForExpectedResult = new ProductInBasketForSession();
        productInBasketForSessionForExpectedResult.setQuantity(6);
        mapForExpectedResult.put(productId, productInBasketForSessionForExpectedResult);
        expectedResult.put("catalogSuccess", mapForExpectedResult);

        Map<String, Map<Integer, ProductInBasketForSession>> result = basketForSessionService
                .calculateQuantityDifferenceToGetProductAndReturnCatalogParam(productId, numberOfOrderedProducts, inputMap);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void calculateQuantityDifferenceToGetProductAndReturnCatalogParam_basketContainsSuchProductButQuantityIsNotOk() {

        int productId = 1;
        int numberOfOrderedProducts = 5;

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productId);
        productDTO.setQuantityInStore(3);

        when(productService.getProduct(productId)).thenReturn(productDTO);

        Map<Integer, ProductInBasketForSession> inputMap = new HashMap<>();
        ProductInBasketForSession inputProductInBasketForSession = new ProductInBasketForSession();
        inputProductInBasketForSession.setQuantity(1);
        inputMap.put(productId, inputProductInBasketForSession);

        Map<String, Map<Integer, ProductInBasketForSession>> expectedResult = new HashMap<>();
        expectedResult.put("catalogFail", null);

        Map<String, Map<Integer, ProductInBasketForSession>> result = basketForSessionService
                .calculateQuantityDifferenceToGetProductAndReturnCatalogParam(productId, numberOfOrderedProducts, inputMap);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void calculateQuantityDifferenceToGetProductAndReturnCatalogParam_basketNotContainsSuchProductAndQuantityIsOk() {

        int productId = 1;
        int numberOfOrderedProducts = 5;

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productId);
        productDTO.setQuantityInStore(10);

        when(productService.getProduct(productId)).thenReturn(productDTO);

        Map<Integer, ProductInBasketForSession> inputMap = new HashMap<>();

        Map<String, Map<Integer, ProductInBasketForSession>> expectedResult = new HashMap<>();
        Map<Integer, ProductInBasketForSession> mapForExpectedResult = new HashMap<>();
        ProductInBasketForSession productInBasketForSessionForExpectedResult = new ProductInBasketForSession();
        productInBasketForSessionForExpectedResult.setQuantity(5);
        mapForExpectedResult.put(productId, productInBasketForSessionForExpectedResult);
        expectedResult.put("catalogSuccess", mapForExpectedResult);

        Map<String, Map<Integer, ProductInBasketForSession>> result = basketForSessionService
                .calculateQuantityDifferenceToGetProductAndReturnCatalogParam(productId, numberOfOrderedProducts, inputMap);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void calculateQuantityDifferenceToGetProductAndReturnCatalogParam_basketNotContainsSuchProductAndQuantityIsNotOk() {

        int productId = 1;
        int numberOfOrderedProducts = 5;

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productId);
        productDTO.setQuantityInStore(3);

        when(productService.getProduct(productId)).thenReturn(productDTO);

        Map<Integer, ProductInBasketForSession> inputMap = new HashMap<>();

        Map<String, Map<Integer, ProductInBasketForSession>> expectedResult = new HashMap<>();
        expectedResult.put("catalogFail", null);

        Map<String, Map<Integer, ProductInBasketForSession>> result = basketForSessionService
                .calculateQuantityDifferenceToGetProductAndReturnCatalogParam(productId, numberOfOrderedProducts, inputMap);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void getSumAndQuantity() {

        int sum = 10000;
        int quantity = 10;

        Map<Integer, ProductInBasketForSession> inputMap = new HashMap<>();
        ProductInBasketForSession firstProduct = new ProductInBasketForSession();
        ProductInBasketForSession secondProduct = new ProductInBasketForSession();
        ProductInBasketForSession thirdProduct = new ProductInBasketForSession();

        firstProduct.setQuantity(2);
        firstProduct.setPrice(2300);
        inputMap.put(1, firstProduct);

        secondProduct.setQuantity(5);
        secondProduct.setPrice(600);
        inputMap.put(2, secondProduct);

        thirdProduct.setQuantity(3);
        thirdProduct.setPrice(800);
        inputMap.put(3, thirdProduct);

        Map<String, Integer> result = basketForSessionService.getSumAndQuantity(inputMap);

        Map<String, Integer> expectedResult = new HashMap<>();
        expectedResult.put("sum", sum);
        expectedResult.put("quantity", quantity);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void addAllOrdersProductsToBasket_productDtoNotNullAndEnoughProducts() {

        List<OrderedProductForHistoryDTO> inputList = new ArrayList<>();
        String productName = "product";
        int productQuantity = 5;

        OrderedProductForHistoryDTO firstProduct = new OrderedProductForHistoryDTO();
        firstProduct.setName(productName);
        firstProduct.setQuantity(productQuantity);
        inputList.add(firstProduct);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productName);
        productEntity.setQuantityInStore(10);

        when(productRepository.findProductsByName(productName)).thenReturn(productEntity);

        Map<Integer, ProductInBasketForSession> result = basketForSessionService.addAllOrdersProductsToBasket(inputList);

        Map<Integer, ProductInBasketForSession> expectedResult = new HashMap<>();
        ProductInBasketForSession productInBasketForSession = new ProductInBasketForSession();
        productInBasketForSession.setProductName(productName);
        productInBasketForSession.setQuantity(productQuantity);
        expectedResult.put(0, productInBasketForSession);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void addAllOrdersProductsToBasket_productDtoNotNullAndNotEnoughProducts() {

        List<OrderedProductForHistoryDTO> inputList = new ArrayList<>();
        String productName = "product";
        int productQuantity = 8;

        OrderedProductForHistoryDTO firstProduct = new OrderedProductForHistoryDTO();
        firstProduct.setName(productName);
        firstProduct.setQuantity(productQuantity);
        inputList.add(firstProduct);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productName);
        productEntity.setQuantityInStore(3);

        when(productRepository.findProductsByName(productName)).thenReturn(productEntity);

        Map<Integer, ProductInBasketForSession> result = basketForSessionService.addAllOrdersProductsToBasket(inputList);

        Map<Integer, ProductInBasketForSession> expectedResult = new HashMap<>();
        ProductInBasketForSession productInBasketForSession = new ProductInBasketForSession();
        productInBasketForSession.setProductName(productName);
        productInBasketForSession.setQuantity(3);
        expectedResult.put(0, productInBasketForSession);

        Assert.assertEquals(expectedResult, result);
    }

}