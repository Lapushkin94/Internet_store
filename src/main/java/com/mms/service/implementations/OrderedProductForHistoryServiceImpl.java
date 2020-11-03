package com.mms.service.implementations;


import com.mms.dto.ClientDTO;
import com.mms.dto.OrderDTO;
import com.mms.dto.OrderedProductForHistoryDTO;
import com.mms.dto.converterDTO.ClientConverter;
import com.mms.dto.converterDTO.OrderConverter;
import com.mms.dto.converterDTO.OrderedProductForHistoryConverter;
import com.mms.repository.interfaces.ClientRepository;
import com.mms.repository.interfaces.OrderRepository;
import com.mms.repository.interfaces.OrderedProductForHistoryRepository;
import com.mms.service.interfaces.OrderedProductForHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;


import static com.mms.dto.converterDTO.OrderedProductForHistoryConverter.toEntity;

@Service
public class OrderedProductForHistoryServiceImpl implements OrderedProductForHistoryService {

    private static final Logger logger = Logger.getLogger(OrderedProductForHistoryServiceImpl.class.getName());

    private OrderedProductForHistoryRepository orderedProductForHistoryRepository;
    private OrderRepository orderRepository;
    private ClientRepository clientRepository;

    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

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
        logger.info("adding product to history table");
        orderedProductForHistoryRepository.saveProduct(toEntity(orderedProductForHistoryDTO));
    }

    @Override
    @Transactional
    public int getProductCount() {
        return orderedProductForHistoryRepository.getProductCount();
    }

    @Override
    @Transactional
    public Map<String, Integer> getTop10ProductsBySoldNumber() {

        logger.info("getting top 10 products stat");

        List<OrderedProductForHistoryDTO> orderedProductForHistoryDTOList = orderedProductForHistoryRepository.findAllProductsWithoutPages().stream()
                .map(OrderedProductForHistoryConverter::toDto)
                .collect(Collectors.toList());

        Map<String, Integer> top10productsMap = new HashMap<>();

        for (OrderedProductForHistoryDTO prod : orderedProductForHistoryDTOList) {
            String productName = prod.getName();
            if (top10productsMap.containsKey(productName)) {
                top10productsMap.put(productName, top10productsMap.get(productName) + prod.getQuantity());
            } else top10productsMap.put(productName, prod.getQuantity());
        }

        return top10productsMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }


    @Override
    @Transactional
    public Map<String, Integer> getTop10clientsByProfit() {

        logger.info("getting top 10 clients stat");

        Map<String, Integer> top10clientsByProfit = new HashMap<>();
        int clientProfit;
        String clientEmail;

        List<ClientDTO> clientDTOList = clientRepository.findAllClientsWithoutPages().stream()
                .map(ClientConverter::toDto)
                .collect(Collectors.toList());

        for (ClientDTO clientDTO : clientDTOList) {

            clientEmail = clientDTO.getEmail();

            List<OrderedProductForHistoryDTO> orderedProductForHistoryDTOList = orderedProductForHistoryRepository
                    .findAllProductsInHistoryByClientEmail(clientEmail).stream()
                    .map(OrderedProductForHistoryConverter::toDto)
                    .collect(Collectors.toList());

            clientProfit = 0;

            for (OrderedProductForHistoryDTO productInHistory : orderedProductForHistoryDTOList) {
                clientProfit += productInHistory.getPrice() * productInHistory.getQuantity();
            }

            if (top10clientsByProfit.containsKey(clientEmail)) {
                top10clientsByProfit.put(clientEmail, top10clientsByProfit.get(clientEmail) + clientProfit);
            } else top10clientsByProfit.put(clientEmail, clientProfit);

        }

        return top10clientsByProfit.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }


    @Override
    @Transactional
    public int getTotalProfitByNumberOfDays(String currentDateMinusNumberOfDays) {

        logger.info("getting total profit by " + currentDateMinusNumberOfDays + " days stat");

        List<OrderDTO> listOrdersByDate = orderRepository.findListOrdersByNumberOfDays(currentDateMinusNumberOfDays).stream()
                .map(OrderConverter::toDto)
                .collect(Collectors.toList());

        int totalProfit = 0;

        for (OrderDTO orderDTO : listOrdersByDate) {

            List<OrderedProductForHistoryDTO> orderedProductForHistoryDTOList = orderedProductForHistoryRepository
                    .findAllProductsInHistoryByOrderId(orderDTO.getId()).stream()
                    .map(OrderedProductForHistoryConverter::toDto)
                    .collect(Collectors.toList());

            for (OrderedProductForHistoryDTO orderedProductForHistoryDTO : orderedProductForHistoryDTOList) {
                totalProfit += orderedProductForHistoryDTO.getPrice() * orderedProductForHistoryDTO.getQuantity();
            }
        }

        return totalProfit;
    }
}
