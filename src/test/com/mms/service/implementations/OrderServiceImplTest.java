package com.mms.service.implementations;

import com.mms.dto.OrderedProductForHistoryDTO;
import com.mms.dto.ProductInBasketForSession;
import com.mms.model.OrderEntity;
import com.mms.model.OrderedProductForHistoryEntity;
import com.mms.model.ProductEntity;
import com.mms.repository.interfaces.OrderRepository;
import com.mms.repository.interfaces.OrderedProductForHistoryRepository;
import com.mms.repository.interfaces.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @InjectMocks
    OrderServiceImpl orderService;

    @Mock
    OrderRepository orderRepository;

    @Mock
    ProductRepository productRepository;

    @Mock
    OrderedProductForHistoryRepository orderedProductForHistoryRepository;

    @Test
    public void calculateProductNumberInStoreAlsoCopyProductInfoToTheHistoryTableAndResetProductBascet_success() {

        int orderId = 1;
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderId);
        when(orderRepository.findOrderById(orderId)).thenReturn(orderEntity);

        int productId = 1;
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productId);
        productEntity.setQuantityInStore(10);
        productEntity.setName("first");
        productEntity.setAlternative_name("firstAltName");
        productEntity.setBrandName("firstBrandName");
        productEntity.setPrice(100);
        productEntity.setColor("black");
        productEntity.setWeight(100);
        productEntity.setCountry("firstCountry");
        productEntity.setDescription("firstDescription");
        when(productRepository.findProductByIdTransactional(productId)).thenReturn(productEntity);


        Map<Integer, ProductInBasketForSession> inputMap = new HashMap<>();
        ProductInBasketForSession productInBasketForSession = new ProductInBasketForSession();
        productInBasketForSession.setQuantity(5);
        inputMap.put(productId, productInBasketForSession);

        doNothing().when(productRepository).updateProduct(productEntity);

        OrderedProductForHistoryDTO orderedProductForHistoryDTO = new OrderedProductForHistoryDTO();
        orderedProductForHistoryDTO.setName(productEntity.getName());
        orderedProductForHistoryDTO.setAlternative_name(productEntity.getAlternative_name());
        orderedProductForHistoryDTO.setBrandName(productEntity.getBrandName());
        orderedProductForHistoryDTO.setPrice(productEntity.getPrice());
        orderedProductForHistoryDTO.setColor(productEntity.getColor());
        orderedProductForHistoryDTO.setWeight(productEntity.getWeight());
        orderedProductForHistoryDTO.setCountry(productEntity.getCountry());
        orderedProductForHistoryDTO.setDescription(productEntity.getDescription());
        orderedProductForHistoryDTO.setOrderInHistory(orderEntity);

        String result = orderService
                .calculateProductNumberInStoreAlsoCopyProductInfoToTheHistoryTableAndResetProductBascet(inputMap, orderId);

        String expectedResult = "completedSuccessfully";

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void calculateProductNumberInStoreAlsoCopyProductInfoToTheHistoryTableAndResetProductBascet_fail() {

        int orderId = 1;
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderId);
        when(orderRepository.findOrderById(orderId)).thenReturn(orderEntity);

        int productId = 1;
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productId);
        productEntity.setQuantityInStore(5);
        productEntity.setName("first");
        productEntity.setAlternative_name("firstAltName");
        productEntity.setBrandName("firstBrandName");
        productEntity.setPrice(100);
        productEntity.setColor("black");
        productEntity.setWeight(100);
        productEntity.setCountry("firstCountry");
        productEntity.setDescription("firstDescription");
        when(productRepository.findProductByIdTransactional(productId)).thenReturn(productEntity);


        Map<Integer, ProductInBasketForSession> inputMap = new HashMap<>();
        ProductInBasketForSession productInBasketForSession = new ProductInBasketForSession();
        productInBasketForSession.setQuantity(10);
        inputMap.put(productId, productInBasketForSession);

        OrderedProductForHistoryDTO orderedProductForHistoryDTO = new OrderedProductForHistoryDTO();
        orderedProductForHistoryDTO.setName(productEntity.getName());
        orderedProductForHistoryDTO.setAlternative_name(productEntity.getAlternative_name());
        orderedProductForHistoryDTO.setBrandName(productEntity.getBrandName());
        orderedProductForHistoryDTO.setPrice(productEntity.getPrice());
        orderedProductForHistoryDTO.setColor(productEntity.getColor());
        orderedProductForHistoryDTO.setWeight(productEntity.getWeight());
        orderedProductForHistoryDTO.setCountry(productEntity.getCountry());
        orderedProductForHistoryDTO.setDescription(productEntity.getDescription());
        orderedProductForHistoryDTO.setOrderInHistory(orderEntity);

        String result;
        try {
            result = orderService
                    .calculateProductNumberInStoreAlsoCopyProductInfoToTheHistoryTableAndResetProductBascet(inputMap, orderId);
        }
        catch (RuntimeException exc) {
            result = "interrupted";
        }

        String expectedResult = "interrupted";

        Assert.assertEquals(expectedResult, result);
    }


    @Test
    public void getProductsToAddByOrderId_success() {

        int orderId = 1;
        String productName = "first";

        List<OrderedProductForHistoryEntity> listToReturn = new ArrayList<>();
        OrderedProductForHistoryEntity orderedProductForHistoryEntity = new OrderedProductForHistoryEntity();
        orderedProductForHistoryEntity.setName(productName);
        orderedProductForHistoryEntity.setQuantity(5);
        orderedProductForHistoryEntity.setAlternative_name("firstAlternative");
        orderedProductForHistoryEntity.setBrandName("firstBrandName");
        orderedProductForHistoryEntity.setColor("black");
        orderedProductForHistoryEntity.setWeight(100);
        orderedProductForHistoryEntity.setCountry("firstCountry");
        listToReturn.add(orderedProductForHistoryEntity);

        when(orderedProductForHistoryRepository.findAllProductsInHistoryByOrderId(orderId)).thenReturn(listToReturn);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productName);
        productEntity.setQuantityInStore(10);
        productEntity.setAlternative_name("firstAlternative");
        productEntity.setBrandName("firstBrandName");
        productEntity.setColor("black");
        productEntity.setWeight(100);
        productEntity.setCountry("firstCountry");

        when(productRepository.findProductsByName(productName)).thenReturn(productEntity);

        List<OrderedProductForHistoryDTO> result = orderService.getProductsToAddByOrderId(orderId);

        List<OrderedProductForHistoryDTO> expectedResult = new ArrayList<>();
        OrderedProductForHistoryDTO orderedProductForHistoryDTO = new OrderedProductForHistoryDTO();
        orderedProductForHistoryDTO.setName(productName);
        orderedProductForHistoryDTO.setQuantity(5);
        orderedProductForHistoryDTO.setAlternative_name("firstAlternative");
        orderedProductForHistoryDTO.setBrandName("firstBrandName");
        orderedProductForHistoryDTO.setColor("black");
        orderedProductForHistoryDTO.setWeight(100);
        orderedProductForHistoryDTO.setCountry("firstCountry");
        expectedResult.add(orderedProductForHistoryDTO);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void getProductsToAddByOrderId_fail() {

        int orderId = 1;
        String productName = "first";

        List<OrderedProductForHistoryEntity> listToReturn = new ArrayList<>();
        OrderedProductForHistoryEntity orderedProductForHistoryEntity = new OrderedProductForHistoryEntity();
        orderedProductForHistoryEntity.setName(productName);
        orderedProductForHistoryEntity.setQuantity(10);
        orderedProductForHistoryEntity.setAlternative_name("firstAlternative");
        orderedProductForHistoryEntity.setBrandName("firstBrandName");
        orderedProductForHistoryEntity.setColor("black");
        orderedProductForHistoryEntity.setWeight(100);
        orderedProductForHistoryEntity.setCountry("firstCountry");
        listToReturn.add(orderedProductForHistoryEntity);

        when(orderedProductForHistoryRepository.findAllProductsInHistoryByOrderId(orderId)).thenReturn(listToReturn);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setName("failName");
        productEntity.setQuantityInStore(5);
        productEntity.setAlternative_name("firstAlternative");
        productEntity.setBrandName("firstBrandName");
        productEntity.setColor("black");
        productEntity.setWeight(100);
        productEntity.setCountry("firstCountry");

        when(productRepository.findProductsByName(productName)).thenThrow(NoResultException.class);

        List<OrderedProductForHistoryDTO> result = orderService.getProductsToAddByOrderId(orderId);

        List<OrderedProductForHistoryDTO> expectedResult = new ArrayList<>();

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void getMissingProductsByOrderId_success() {

        int orderId = 1;
        String productName = "first";

        List<OrderedProductForHistoryEntity> listToReturn = new ArrayList<>();
        OrderedProductForHistoryEntity orderedProductForHistoryEntity = new OrderedProductForHistoryEntity();
        orderedProductForHistoryEntity.setName(productName);
        orderedProductForHistoryEntity.setQuantity(5);
        orderedProductForHistoryEntity.setAlternative_name("firstAlternative");
        orderedProductForHistoryEntity.setBrandName("firstBrandName");
        orderedProductForHistoryEntity.setColor("black");
        orderedProductForHistoryEntity.setWeight(100);
        orderedProductForHistoryEntity.setCountry("firstCountry");
        listToReturn.add(orderedProductForHistoryEntity);

        when(orderedProductForHistoryRepository.findAllProductsInHistoryByOrderId(orderId)).thenReturn(listToReturn);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productName);
        productEntity.setQuantityInStore(5);
        productEntity.setAlternative_name("firstAlternative");
        productEntity.setBrandName("firstBrandName");
        productEntity.setColor("black");
        productEntity.setWeight(100);
        productEntity.setCountry("firstCountry");

        when(productRepository.findProductsByName(productName)).thenThrow(NoResultException.class);

        List<OrderedProductForHistoryDTO> result = orderService.getMissingProductsByOrderId(orderId);

        List<OrderedProductForHistoryDTO> expectedResult = new ArrayList<>();
        OrderedProductForHistoryDTO orderedProductForHistoryDTO = new OrderedProductForHistoryDTO();
        orderedProductForHistoryDTO.setName(productName);
        orderedProductForHistoryDTO.setQuantity(5);
        orderedProductForHistoryDTO.setAlternative_name("firstAlternative");
        orderedProductForHistoryDTO.setBrandName("firstBrandName");
        orderedProductForHistoryDTO.setColor("black");
        orderedProductForHistoryDTO.setWeight(100);
        orderedProductForHistoryDTO.setCountry("firstCountry");
        expectedResult.add(orderedProductForHistoryDTO);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void getMissingProductsByOrderId_fail() {

        int orderId = 1;
        String productName = "first";

        List<OrderedProductForHistoryEntity> listToReturn = new ArrayList<>();
        OrderedProductForHistoryEntity orderedProductForHistoryEntity = new OrderedProductForHistoryEntity();
        orderedProductForHistoryEntity.setName(productName);
        orderedProductForHistoryEntity.setQuantity(10);
        orderedProductForHistoryEntity.setAlternative_name("firstAlternative");
        orderedProductForHistoryEntity.setBrandName("firstBrandName");
        orderedProductForHistoryEntity.setColor("black");
        orderedProductForHistoryEntity.setWeight(100);
        orderedProductForHistoryEntity.setCountry("firstCountry");
        listToReturn.add(orderedProductForHistoryEntity);

        when(orderedProductForHistoryRepository.findAllProductsInHistoryByOrderId(orderId)).thenReturn(listToReturn);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productName);
        productEntity.setQuantityInStore(10);
        productEntity.setAlternative_name("firstAlternative");
        productEntity.setBrandName("firstBrandName");
        productEntity.setColor("black");
        productEntity.setWeight(100);
        productEntity.setCountry("firstCountry");

        when(productRepository.findProductsByName(productName)).thenReturn(productEntity);

        List<OrderedProductForHistoryDTO> result = orderService.getMissingProductsByOrderId(orderId);

        List<OrderedProductForHistoryDTO> expectedResult = new ArrayList<>();

        Assert.assertEquals(expectedResult, result);
    }

}