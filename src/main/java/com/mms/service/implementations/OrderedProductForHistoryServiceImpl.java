package com.mms.service.implementations;


import com.mms.dto.OrderedProductForHistoryDTO;
import com.mms.dto.converterDTO.OrderedProductForHistoryConverter;
import com.mms.repository.interfaces.OrderedProductForHistoryRepository;
import com.mms.service.interfaces.OrderedProductForHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.mms.dto.converterDTO.OrderedProductForHistoryConverter.toEntity;

@Service
public class OrderedProductForHistoryServiceImpl implements OrderedProductForHistoryService {

    private OrderedProductForHistoryRepository orderedProductForHistoryRepository;

    @Autowired
    public void setOrderedProductForHistoryRepository(OrderedProductForHistoryRepository orderedProductForHistoryRepository) {
        this.orderedProductForHistoryRepository = orderedProductForHistoryRepository;
    }


    @Override
    @Transactional
    public List<OrderedProductForHistoryDTO> getAllProductsWithPages(int page) {
        return orderedProductForHistoryRepository.findAllProductsWithPages(page).stream()
                .map(OrderedProductForHistoryConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<OrderedProductForHistoryDTO> getAllProductsWithoutPages() {
        return orderedProductForHistoryRepository.findAllProductsWithoutPages().stream()
                .map(OrderedProductForHistoryConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addProduct(OrderedProductForHistoryDTO orderedProductForHistoryDTO) {
        orderedProductForHistoryRepository.saveProduct(toEntity(orderedProductForHistoryDTO));
    }

    @Override
    @Transactional
    public int getProductCount() {
        return orderedProductForHistoryRepository.getProductCount();
    }
}
