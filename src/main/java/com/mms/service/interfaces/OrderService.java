package com.mms.service.interfaces;

import com.mms.dto.OrderDTO;
import com.mms.dto.OrderedProductForHistoryDTO;
import com.mms.dto.ProductInBascetDTO;

import java.util.List;

public interface OrderService {

    List<OrderDTO> getAllOrders(int page);
    void addOrder(OrderDTO orderDTO);
    void deleteOrder(OrderDTO orderDTO);
    void editOrder(OrderDTO orderDTO);
    OrderDTO getOrder(int id);
    int getOrderCount();
    String calculateProductNumberInStoreAlsoCopyProductInfoToTheHistoryTableAndResetProductBascet(List<ProductInBascetDTO> productInBascetDTOList, int orderId);
    int addOrderAndReturnId(OrderDTO orderDTO);
    int getOrderCountByClientId(int clientId);
    int getProductsCountByOrdersId(int orderId);
    List<OrderedProductForHistoryDTO> getOrdersProductHistoryByOrderId(int orderId, int orderHistoryPage);
    List<OrderedProductForHistoryDTO> getOrdersProductHistoryByOrderIdWithoutPages(int orderId);
    List<OrderedProductForHistoryDTO> getProductsToAddByOrderId(int id);
    List<OrderedProductForHistoryDTO> getEditedProductsByOrderId(int id);
    List<OrderedProductForHistoryDTO> getMissingProductsByOrderId(int id);

}
