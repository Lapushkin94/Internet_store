package com.mms.service.implementations;

import com.mms.dto.*;
import com.mms.dto.converterDTO.*;
import com.mms.model.OrderEntity;
import com.mms.repository.interfaces.OrderRepository;
import com.mms.repository.interfaces.OrderedProductForHistoryRepository;
import com.mms.repository.interfaces.ProductInBascetRepository;
import com.mms.repository.interfaces.ProductRepository;
import com.mms.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.mms.dto.converterDTO.OrderConverter.toDto;
import static com.mms.dto.converterDTO.OrderConverter.toEntity;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private ProductInBascetRepository productInBascetRepository;
    private ProductRepository productRepository;
    private OrderedProductForHistoryRepository orderedProductForHistoryRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setProductInBascetRepository(ProductInBascetRepository productInBascetRepository) {
        this.productInBascetRepository = productInBascetRepository;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setOrderedProductForHistoryRepository(OrderedProductForHistoryRepository orderedProductForHistoryRepository) {
        this.orderedProductForHistoryRepository = orderedProductForHistoryRepository;
    }

    @Override
    @Transactional
    public List<OrderDTO> getAllOrders(int page) {
        List<OrderDTO> orderDTOList = orderRepository.findAllOrders(page).stream()
                .map(OrderConverter::toDto)
                .collect(Collectors.toList());
        Collections.reverse(orderDTOList);
        return orderDTOList;
    }

    @Override
    @Transactional
    public void addOrder(OrderDTO orderDTO) {
        orderRepository.saveOrder(toEntity(orderDTO));
    }

    @Override
    @Transactional
    public void deleteOrder(OrderDTO orderDTO) {
        orderRepository.deleteOrder(toEntity(orderDTO));
    }

    @Override
    @Transactional
    public void editOrder(OrderDTO orderDTO) {
        orderRepository.updateOrder(toEntity(orderDTO));
    }

    @Override
    @Transactional
    public OrderDTO getOrder(int id) {
        return toDto(orderRepository.findOrderById(id));
    }

    @Override
    @Transactional
    public int getOrderCount() {
        return orderRepository.getOrderCount();
    }

    @Override
    @Transactional
    public String calculateProductNumberInStoreAlsoCopyProductInfoToTheHistoryTableAndResetProductBascet(List<ProductInBascetDTO> productInBascetDTOList, int orderId) {

        for (ProductInBascetDTO prod : productInBascetDTOList) {
            if (ProductConverter.toDto(prod.getProduct()).getQuantityInStore() < prod.getQuantity()) {
                return "notEnough=" + ProductConverter.toDto(prod.getProduct()).getId();
            }
        }

        ProductDTO productDTO;
        OrderedProductForHistoryDTO orderedProductForHistoryDTO;
        OrderEntity orderEntity = orderRepository.findOrderById(orderId);

        for (ProductInBascetDTO productInBascetDTO : productInBascetDTOList) {

            productDTO = ProductConverter.toDto(productInBascetDTO.getProduct());
            productDTO.setQuantityInStore(productInBascetDTO.getProduct().getQuantityInStore() - productInBascetDTO.getQuantity());
            productRepository.updateProduct(ProductConverter.toEntity(productDTO));

            orderedProductForHistoryDTO = new OrderedProductForHistoryDTO();
            orderedProductForHistoryDTO.setName(productInBascetDTO.getProduct().getName());
            orderedProductForHistoryDTO.setAlternative_name(productInBascetDTO.getProduct().getAlternative_name());
            orderedProductForHistoryDTO.setBrandName(productInBascetDTO.getProduct().getBrandName());
            orderedProductForHistoryDTO.setPrice(productInBascetDTO.getProduct().getPrice());
            orderedProductForHistoryDTO.setColor(productInBascetDTO.getProduct().getColor());
            orderedProductForHistoryDTO.setWeight(productInBascetDTO.getProduct().getWeight());
            orderedProductForHistoryDTO.setCountry(productInBascetDTO.getProduct().getCountry());
            orderedProductForHistoryDTO.setDescription(productInBascetDTO.getProduct().getDescription());

            orderedProductForHistoryDTO.setOrderInHistory(orderEntity);

            orderedProductForHistoryDTO.setQuantity(productInBascetDTO.getQuantity());
            orderedProductForHistoryRepository.saveProduct(OrderedProductForHistoryConverter.toEntity(orderedProductForHistoryDTO));

            productInBascetRepository.deleteProductInBascet(ProductInBascetConverter.toEntity(productInBascetDTO));
        }

        return "completedSuccessfully";
    }

    @Override
    @Transactional
    public int addOrderAndReturnId(OrderDTO orderDTO) {
        return orderRepository.saveOrderAndReturnId(OrderConverter.toEntity(orderDTO));
    }

    @Override
    @Transactional
    public int getOrderCountByClientId(int clientId) {
        return orderRepository.getOrderCountByClientId(clientId);
    }

    @Override
    @Transactional
    public int getProductsCountByOrdersId(int orderId) {
        return orderRepository.getProductsCountByOrdersId(orderId);
    }

    @Override
    @Transactional
    public List<OrderedProductForHistoryDTO> getOrdersProductHistoryByOrderId(int orderId, int orderHistoryPage) {
        return orderRepository.findOrdersProductHistoryByOrderId(orderId, orderHistoryPage).stream()
                .map(OrderedProductForHistoryConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<OrderedProductForHistoryDTO> getOrdersProductHistoryByOrderIdWithoutPages(int orderId) {
        return orderRepository.findOrdersProductHistoryByOrderIdWithoutPages(orderId).stream()
                .map(OrderedProductForHistoryConverter::toDto)
                .collect(Collectors.toList());
    }

}
