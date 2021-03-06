package com.mms.service.implementations;

import com.mms.dto.OrderStatusDTO;
import com.mms.dto.converterDTO.OrderStatusConverter;
import com.mms.repository.interfaces.OrderStatusRepository;
import com.mms.service.interfaces.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.mms.dto.converterDTO.OrderStatusConverter.toDto;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

    private static final Logger logger = Logger.getLogger(OrderStatusServiceImpl.class.getName());

    private OrderStatusRepository orderStatusRepository;

    @Autowired
    public void setOrderStatusRepository(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    @Transactional
    public OrderStatusDTO getOpenedStatus() {
        return toDto(orderStatusRepository.findOpenedStatus());
    }

    @Override
    @Transactional
    public OrderStatusDTO getInProcessStatus() {
        return toDto(orderStatusRepository.findInProcessStatus());
    }

    @Override
    @Transactional
    public OrderStatusDTO getClosedStatus() {
        return toDto(orderStatusRepository.findClosedStatus());
    }

    @Override
    @Transactional
    public List<OrderStatusDTO> getAllOrderStatus() {
        return orderStatusRepository.findAllOrderStatus().stream()
                .map(OrderStatusConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderStatusDTO getOrderStatusByName(String name) {
        logger.info("getting status by name " + name);
        return OrderStatusConverter.toDto(orderStatusRepository.getOrderStatusByName(name));
    }
}
