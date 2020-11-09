package com.mms.service.interfaces;

import com.mms.dto.OrderedProductForHistoryDTO;
import com.mms.dto.ProductInBasketForSession;

import java.util.List;
import java.util.Map;

public interface BasketForSessionService {

    Map<Integer, ProductInBasketForSession> removeProductFromBasketByIdAndReturnActualBasket(Map<Integer, ProductInBasketForSession> currentBasket,
                                                                                             int id);

    Map<String, Map<Integer, ProductInBasketForSession>> calculateQuantityDifferenceToGetProductAndReturnCatalogParam(int id,
                                                                                                                      int numberOfOrderedProducts,
                                                                                                                      Map<Integer, ProductInBasketForSession> productInBasketForSessionIntegerMap);

    Map<Integer, ProductInBasketForSession> resetBasket(Map<Integer, ProductInBasketForSession> sessionMap);

    Map<String, Integer> getSumAndQuantity(Map<Integer, ProductInBasketForSession> productsInBasketForSession);

    Map<Integer, ProductInBasketForSession> addAllOrdersProductsToBasket(List<OrderedProductForHistoryDTO> productsToAdd);

    Map<String, String> getQuantityDifferenceForSecondaryOrderedProducts(List<OrderedProductForHistoryDTO> productsToAdd);

    Map<Integer, ProductInBasketForSession> getTemporaryBasketByPage(int productInBascetListPage,
                                                                     Map<Integer, ProductInBasketForSession> inputBasket);

}
