package com.mms.service.implementations;

import com.mms.dto.OrderStatusDTO;
import com.mms.repository.interfaces.OrderStatusRepository;
import com.mms.service.interfaces.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.mms.dto.converterDTO.OrderStatusConverter.toDto;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

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
}
