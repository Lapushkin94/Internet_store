package com.mms.service.implementations;

import com.mms.dto.ProductForStand;
import com.mms.service.interfaces.OrderedProductForHistoryService;
import com.mms.service.interfaces.ProductForStandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class ProductsForStandServiceImpl implements ProductForStandService {

    private OrderedProductForHistoryService orderedProductForHistoryService;

    @Lazy
    @Autowired
    private JmsTemplate jmsTemplate;

    private Logger logger = Logger.getLogger(ProductsForStandServiceImpl.class.getName());

    @Autowired
    public void setOrderedProductForHistoryService(OrderedProductForHistoryService orderedProductForHistoryService) {
        this.orderedProductForHistoryService = orderedProductForHistoryService;
    }

    @Override
    @Transactional
    public List<ProductForStand> getProductsForStandList() {

        Map<String, Integer> productsForStandMap = orderedProductForHistoryService.getTop10ProductsBySoldNumberOptimizedVersion();

        List<ProductForStand> productForStandList = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : productsForStandMap.entrySet()) {

            ProductForStand productForStand = new ProductForStand();
            productForStand.setName(entry.getKey());
            productForStand.setPrice(entry.getValue());

            productForStandList.add(productForStand);
        }

        return productForStandList;
    }

    @Override
    @Transactional
    public void sendMessageToStandApp() {
        logger.info("sending message to second app");
        try {
            jmsTemplate.send(session -> session.createTextMessage("DB changes!"));
        }

        // need to change exception type
        catch (Exception exc) {
            logger.info(exc.getMessage());
        }
    }

    @Override
    @Transactional
    public void initMessage() {
        logger.info("init message");
        try {
            sendMessageToStandApp();
        }

        // need to change exception type
        catch (Exception exc) {
            logger.info(exc.getMessage());
        }
    }
}
