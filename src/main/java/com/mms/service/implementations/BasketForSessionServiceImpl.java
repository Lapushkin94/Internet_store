package com.mms.service.implementations;

import com.mms.dto.OrderedProductForHistoryDTO;
import com.mms.dto.ProductDTO;
import com.mms.dto.ProductInBasketForSession;
import com.mms.dto.converterDTO.ProductConverter;
import com.mms.repository.interfaces.ProductRepository;
import com.mms.service.interfaces.BasketForSessionService;
import com.mms.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class BasketForSessionServiceImpl implements BasketForSessionService {

    private static final Logger logger = Logger.getLogger(ProductInBascetServiceImpl.class.getName());

    private ProductService productService;
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Override
    @Transactional
    public Map<Integer, ProductInBasketForSession> removeProductFromBasketByIdAndReturnActualBasket(Map<Integer, ProductInBasketForSession> currentBasket, int id) {

        logger.info("removing product from basket");
        currentBasket.remove(id);

        return currentBasket;
    }

    @Override
    @Transactional
    public Map<String, Map<Integer, ProductInBasketForSession>> calculateQuantityDifferenceToGetProductAndReturnCatalogParam(int productId,
                                                                                                                             int numberOfOrderedProducts,
                                                                                                                             Map<Integer, ProductInBasketForSession> prodsInBasket) {

        logger.info("calculating quantity difference");

        Map<String, Map<Integer, ProductInBasketForSession>> result = new HashMap<>();

        if (prodsInBasket.containsKey(productId)) {
            if ((prodsInBasket.get(productId).getQuantity() + numberOfOrderedProducts) > productService.getProduct(productId).getQuantityInStore()) {
                logger.info("not enough products in store");
                result.put("catalogFail", null);
            } else {
                ProductInBasketForSession productInBasketForSession = prodsInBasket.get(productId);
                productInBasketForSession.setQuantity(productInBasketForSession.getQuantity() + numberOfOrderedProducts);
                prodsInBasket.put(productId, productInBasketForSession);
                result.put("catalogSuccess", prodsInBasket);
            }
        } else {
            if (numberOfOrderedProducts > productService.getProduct(productId).getQuantityInStore()) {
                logger.info("not enough products in store");
                result.put("catalogFail", null);
            } else {
                ProductInBasketForSession productInBasketForSession = new ProductInBasketForSession();
                ProductDTO productDTO = productService.getProduct(productId);
                productInBasketForSession.setProductName(productDTO.getName());
                productInBasketForSession.setQuantity(numberOfOrderedProducts);
                productInBasketForSession.setPrice(productDTO.getPrice());
                productInBasketForSession.setAlternative_name(productDTO.getAlternative_name());
                productInBasketForSession.setBrandName(productDTO.getBrandName());
                productInBasketForSession.setColor(productDTO.getColor());
                productInBasketForSession.setWeight(productDTO.getWeight());
                productInBasketForSession.setCountry(productDTO.getCountry());
                productInBasketForSession.setDescription(productDTO.getDescription());
                prodsInBasket.put(productId, productInBasketForSession);
                result.put("catalogSuccess", prodsInBasket);
            }
        }

        return result;
    }

    @Override
    @Transactional
    public Map<Integer, ProductInBasketForSession> resetBasket(Map<Integer, ProductInBasketForSession> sessionMap) {
        sessionMap.clear();
        return sessionMap;
    }

    @Override
    @Transactional
    public Map<String, Integer> getSumAndQuantity(Map<Integer, ProductInBasketForSession> productsInBasketForSession) {

        Map<String, Integer> basketParams = new HashMap<>();
        int sum = 0;
        int quantity = 0;

        for (Map.Entry<Integer, ProductInBasketForSession> entry : productsInBasketForSession.entrySet()) {
            sum = sum + (entry.getValue().getPrice()) * (entry.getValue().getQuantity());
            quantity += entry.getValue().getQuantity();
        }

        basketParams.put("sum", sum);
        basketParams.put("quantity", quantity);

        return basketParams;
    }

    @Override
    @Transactional
    public Map<Integer, ProductInBasketForSession> addAllOrdersProductsToBasket(List<OrderedProductForHistoryDTO> productsToAdd) {

        Map<Integer, ProductInBasketForSession> basketResult = new HashMap<>();
        ProductDTO productDTO;
        ProductInBasketForSession productInBasketForSession;

        for (OrderedProductForHistoryDTO orderedProd : productsToAdd) {
            productInBasketForSession = new ProductInBasketForSession();
            productDTO = ProductConverter.toDto(productRepository.findProductsByName(orderedProd.getName()));

            if (productDTO != null) {
                if (productDTO.getQuantityInStore() > 0) {
                    productInBasketForSession.setProductName(orderedProd.getName());
                    productInBasketForSession.setPrice(orderedProd.getPrice());
                    productInBasketForSession.setAlternative_name(orderedProd.getAlternative_name());
                    productInBasketForSession.setBrandName(orderedProd.getBrandName());
                    productInBasketForSession.setColor(orderedProd.getColor());
                    productInBasketForSession.setWeight(orderedProd.getWeight());
                    productInBasketForSession.setCountry(orderedProd.getCountry());
                    productInBasketForSession.setDescription(orderedProd.getDescription());

                    productInBasketForSession.setQuantity(Math.min(productDTO.getQuantityInStore(), orderedProd.getQuantity()));
                    basketResult.put(productDTO.getId(), productInBasketForSession);
                }
            }
        }

        return basketResult;
    }

    @Override
    @Transactional
    public Map<String, String> getQuantityDifferenceForSecondaryOrderedProducts(List<OrderedProductForHistoryDTO> productsToAdd) {

        Map<String, String> quantityDifference = new HashMap<>();
        ProductDTO productDTO;

        for (OrderedProductForHistoryDTO orderedProd : productsToAdd) {
            productDTO = ProductConverter.toDto(productRepository.findProductsByName(orderedProd.getName()));
            if (productDTO.getQuantityInStore() < orderedProd.getQuantity()) {
                quantityDifference.put(orderedProd.getName(), productDTO.getQuantityInStore() + " / " + orderedProd.getQuantity());
            }
        }

        return quantityDifference;
    }


    @Override
    @Transactional
    public Map<Integer, ProductInBasketForSession> getTemporaryBasketByPage(int productInBascetListPage, Map<Integer, ProductInBasketForSession> inputBasket) {

        Map<Integer, ProductInBasketForSession> result = new HashMap<>();
        int pageCriteriaMin = 1 + 10 * (productInBascetListPage - 1);
        int pageCriteriaMax = 10 * productInBascetListPage;
        int counter = 1;

        for (Map.Entry<Integer, ProductInBasketForSession> entry : inputBasket.entrySet()) {
            if ((counter >= pageCriteriaMin) && (counter <= pageCriteriaMax)) {
                result.put(entry.getKey(), entry.getValue());
            }
            if (counter == pageCriteriaMax) return result;
            counter++;
        }

        return result;
    }
}
