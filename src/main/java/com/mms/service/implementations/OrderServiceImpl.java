package com.mms.service.implementations;

import com.mms.dto.OrderDTO;
import com.mms.dto.converterDTO.OrderConverter;
import com.mms.repository.interfaces.OrderRepository;
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

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
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

}
