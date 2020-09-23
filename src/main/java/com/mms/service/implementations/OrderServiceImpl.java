package com.mms.service.implementations;

import com.mms.dto.OrderDTO;
import com.mms.dto.ProductDTO;
import com.mms.dto.ProductInBascetDTO;
import com.mms.dto.converterDTO.OrderConverter;
import com.mms.dto.converterDTO.ProductConverter;
import com.mms.dto.converterDTO.ProductInBascetConverter;
import com.mms.repository.interfaces.OrderRepository;
import com.mms.repository.interfaces.ProductInBascetRepository;
import com.mms.repository.interfaces.ProductRepository;
import com.mms.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.mms.dto.converterDTO.OrderConverter.toDto;
import static com.mms.dto.converterDTO.OrderConverter.toEntity;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private ProductInBascetRepository productInBascetRepository;
    private ProductRepository productRepository;

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

    @Override
    @Transactional
    public List<OrderDTO> getAllOrders(int page) {
        return orderRepository.findAllOrders(page).stream()
                .map(OrderConverter::toDto)
                .collect(Collectors.toList());
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

        for (ProductInBascetDTO productInBascetDTO : productInBascetDTOList) {

            productDTO = ProductConverter.toDto(productInBascetDTO.getProduct());
            productDTO.setQuantityInStore(productInBascetDTO.getProduct().getQuantityInStore() - productInBascetDTO.getQuantity());
            productRepository.updateProduct(ProductConverter.toEntity(productDTO));

            // добавить код переноса productInBascet в productHistory
            // привязать orderId к перенесенной копии

            productInBascetRepository.deleteProductInBascet(ProductInBascetConverter.toEntity(productInBascetDTO));
        }

        return "completedSuccessfully";
    }
}
