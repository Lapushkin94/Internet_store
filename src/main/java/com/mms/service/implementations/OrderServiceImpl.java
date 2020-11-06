package com.mms.service.implementations;

import com.mms.dto.*;
import com.mms.dto.converterDTO.*;
import com.mms.model.OrderEntity;
import com.mms.model.ProductEntity;
import com.mms.repository.interfaces.OrderRepository;
import com.mms.repository.interfaces.OrderedProductForHistoryRepository;
import com.mms.repository.interfaces.ProductInBascetRepository;
import com.mms.repository.interfaces.ProductRepository;
import com.mms.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.mms.dto.converterDTO.OrderConverter.toDto;
import static com.mms.dto.converterDTO.OrderConverter.toEntity;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = Logger.getLogger(OrderStatusServiceImpl.class.getName());

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
        return orderRepository.findAllOrders(page).stream()
                .map(OrderConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addOrder(OrderDTO orderDTO) {
        logger.info("adding order");
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
        logger.info("editing order");
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
    public String createOrderAndReturnResult(Map<Integer, ProductInBasketForSession> productsInBasket, int orderId) {
        try {
            logger.info("start most long method");
            return calculateProductNumberInStoreAlsoCopyProductInfoToTheHistoryTableAndResetProductBascet(productsInBasket, orderId);
        }

        // need to edit Exception type
        catch (RuntimeException exc) {
            logger.info("Not enough products" + exc.getMessage());
            return "NotEnoughProducts";
        }
    }

    @Override
    @Transactional
    public String calculateProductNumberInStoreAlsoCopyProductInfoToTheHistoryTableAndResetProductBascet(Map<Integer, ProductInBasketForSession> productsInBasket, int orderId) {

        logger.info("creating order history " + orderId);

        ProductDTO productDTO;
        OrderedProductForHistoryDTO orderedProductForHistoryDTO;
        OrderEntity orderEntity = orderRepository.findOrderById(orderId);

        ProductEntity productEntity;

        for (Map.Entry<Integer, ProductInBasketForSession> entry : productsInBasket.entrySet()) {

            productEntity = productRepository.findProductByIdTransactional(entry.getKey());
            productDTO = ProductConverter.toDto(productEntity);


            if (productDTO.getQuantityInStore() < entry.getValue().getQuantity()) {
                logger.info("fail quantity difference");
                orderRepository.deleteOrder(orderEntity);
                throw new RuntimeException();
            }

//            productDTO.setQuantityInStore(productDTO.getQuantityInStore() - entry.getValue().getQuantity());
//            productRepository.updateProduct(ProductConverter.toEntity(productDTO));
            productEntity.setQuantityInStore(productEntity.getQuantityInStore() - entry.getValue().getQuantity());
            productRepository.updateProduct(productEntity);

            orderedProductForHistoryDTO = new OrderedProductForHistoryDTO();
            orderedProductForHistoryDTO.setName(entry.getValue().getProductName());
            orderedProductForHistoryDTO.setAlternative_name(entry.getValue().getAlternative_name());
            orderedProductForHistoryDTO.setBrandName(entry.getValue().getBrandName());
            orderedProductForHistoryDTO.setPrice(entry.getValue().getPrice());
            orderedProductForHistoryDTO.setColor(entry.getValue().getColor());
            orderedProductForHistoryDTO.setWeight(entry.getValue().getWeight());
            orderedProductForHistoryDTO.setCountry(entry.getValue().getCountry());
            orderedProductForHistoryDTO.setDescription(entry.getValue().getDescription());
            orderedProductForHistoryDTO.setOrderInHistory(orderEntity);
            orderedProductForHistoryDTO.setQuantity(entry.getValue().getQuantity());

            orderedProductForHistoryRepository.saveProduct(OrderedProductForHistoryConverter.toEntity(orderedProductForHistoryDTO));

        }

        return "completedSuccessfully";
    }

    @Override
    @Transactional
    public int addOrderAndReturnId(OrderDTO orderDTO) {
        logger.info("adding order");
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

    @Override
    @Transactional
    public List<OrderedProductForHistoryDTO> getProductsToAddByOrderId(int orderId) {

        List<OrderedProductForHistoryDTO> orderedProductForHistoryDTOList = orderedProductForHistoryRepository
                .findAllProductsInHistoryByOrderId(orderId).stream()
                .map(OrderedProductForHistoryConverter::toDto)
                .collect(Collectors.toList());

        List<OrderedProductForHistoryDTO> productsToAdd = new ArrayList<>();
        ProductDTO productDTO;

        for (OrderedProductForHistoryDTO prod : orderedProductForHistoryDTOList) {
            try {
                productDTO = ProductConverter.toDto(productRepository.findProductsByName(prod.getName()));
                if (
                        (prod.getAlternative_name().equals(productDTO.getAlternative_name()))
                                && (prod.getBrandName().equals(productDTO.getBrandName()))
                                && (prod.getPrice() == productDTO.getPrice())
                                && (prod.getColor().equals(productDTO.getColor()))
                                && (prod.getWeight() == productDTO.getWeight())
                                && (prod.getCountry().equals(productDTO.getCountry()))
                ) {
                    productsToAdd.add(prod);
                }
            } catch (NoResultException exc) {
                logger.info("products is missing, " + exc.getMessage());
            }

        }

        return productsToAdd;
    }

    @Override
    @Transactional
    public List<OrderedProductForHistoryDTO> getEditedProductsByOrderId(int orderId) {

        List<OrderedProductForHistoryDTO> orderedProductForHistoryDTOList = orderedProductForHistoryRepository
                .findAllProductsInHistoryByOrderId(orderId).stream()
                .map(OrderedProductForHistoryConverter::toDto)
                .collect(Collectors.toList());

        List<OrderedProductForHistoryDTO> editedProducts = new ArrayList<>();
        ProductDTO productDTO;

        for (OrderedProductForHistoryDTO prod : orderedProductForHistoryDTOList) {
            try {
                productDTO = ProductConverter.toDto(productRepository.findProductsByName(prod.getName()));
                if (
                        !((prod.getAlternative_name().equals(productDTO.getAlternative_name()))
                                && (prod.getBrandName().equals(productDTO.getBrandName()))
                                && (prod.getPrice() == productDTO.getPrice())
                                && (prod.getColor().equals(productDTO.getColor()))
                                && (prod.getWeight() == productDTO.getWeight())
                                && (prod.getCountry().equals(productDTO.getCountry())))
                ) {
                    editedProducts.add(prod);
                }
            } catch (NoResultException exc) {
                logger.info("product is missing, " + exc.getMessage());
            }

        }

        return editedProducts;
    }

    @Override
    @Transactional
    public List<OrderedProductForHistoryDTO> getMissingProductsByOrderId(int orderId) {

        List<OrderedProductForHistoryDTO> orderedProductForHistoryDTOList = orderedProductForHistoryRepository
                .findAllProductsInHistoryByOrderId(orderId).stream()
                .map(OrderedProductForHistoryConverter::toDto)
                .collect(Collectors.toList());

        List<OrderedProductForHistoryDTO> missingProducts = new ArrayList<>();

        for (OrderedProductForHistoryDTO prod : orderedProductForHistoryDTOList) {
            try {
                productRepository.findProductsByName(prod.getName());
            } catch (NoResultException exc) {
                logger.info(exc.getMessage());
                missingProducts.add(prod);
            }

        }

        return missingProducts;
    }
}
