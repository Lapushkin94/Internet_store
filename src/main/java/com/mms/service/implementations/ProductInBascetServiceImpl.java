package com.mms.service.implementations;

import com.mms.dto.OrderedProductForHistoryDTO;
import com.mms.dto.ProductDTO;
import com.mms.dto.ProductInBascetDTO;
import com.mms.dto.converterDTO.ProductConverter;
import com.mms.dto.converterDTO.ProductInBascetConverter;
import com.mms.model.ProductEntity;
import com.mms.model.ProductInBascetEntity;
import com.mms.repository.interfaces.ProductInBascetRepository;
import com.mms.repository.interfaces.ProductRepository;
import com.mms.service.interfaces.ProductInBascetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.mms.dto.converterDTO.ProductInBascetConverter.toDto;
import static com.mms.dto.converterDTO.ProductInBascetConverter.toEntity;

@Service
public class ProductInBascetServiceImpl implements ProductInBascetService {

    private static final Logger logger = Logger.getLogger(ProductInBascetServiceImpl.class.getName());

    private ProductInBascetRepository productInBascetRepository;
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setProductInBascetRepository(ProductInBascetRepository productInBascetRepository) {
        this.productInBascetRepository = productInBascetRepository;
    }

    @Override
    @Transactional
    public List<ProductInBascetDTO> getAllProductsInBascet(int page) {
        return productInBascetRepository.findAllProductsInBascet(page).stream()
                .map(ProductInBascetConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ProductInBascetDTO> getAllProductsInBascetWithoutPages() {
        return productInBascetRepository.findAllProductsInBascetWithoutPages().stream()
                .map(ProductInBascetConverter::toDto)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public void addProduct(ProductInBascetDTO productInBascetDTO, int numberOfOrderedProducts) {

        productInBascetDTO.setQuantity(numberOfOrderedProducts);

        //needs refactor
        try {
            logger.info("updating product in basket quantity " + numberOfOrderedProducts);
            ProductInBascetEntity productInBascetEntity =
                    productInBascetRepository.findProductInBascetByProductId(productInBascetDTO.getProduct().getId());
            productInBascetEntity.setQuantity(productInBascetEntity.getQuantity() + numberOfOrderedProducts);
            productInBascetRepository.updateProduct(productInBascetEntity);
        } catch (NoResultException exc) {
            logger.info("adding new product in basket " + productInBascetDTO.getId());
            productInBascetRepository.saveProduct(toEntity(productInBascetDTO));
        }

    }

    @Override
    @Transactional
    public void deleteProduct(ProductInBascetDTO productInBascetDTO) {
        productInBascetRepository.deleteProductInBascet(toEntity(productInBascetDTO));
    }

    @Override
    @Transactional
    public void editProduct(ProductInBascetDTO productInBascetDTO) {
        logger.info("editing product in basket " + productInBascetDTO.getId());
        productInBascetRepository.updateProduct(toEntity(productInBascetDTO));
    }

    @Override
    @Transactional
    public ProductInBascetDTO getProduct(int id) {
        return toDto(productInBascetRepository.findProductInBascetById(id));
    }

    @Override
    @Transactional
    public int getProductCount() {
        return productInBascetRepository.getProductInBascetCount();
    }

    @Override
    @Transactional
    public String checkQuantityDifferenceThenAddProductInBascet(ProductInBascetDTO productInBascetDTO, int numberOfOrderedProducts) {

        // needs refactor
        try {
            logger.info("adding product to bascet and calculate quantity in store");
            if ((productInBascetRepository
                    .findProductInBascetByProductId(productInBascetDTO.getProduct().getId())
                    .getQuantity() + numberOfOrderedProducts) > productInBascetDTO.getProduct().getQuantityInStore()) {
                logger.info("not enough products in store");
                return "catalogFalse";
            } else {
                logger.info("adding product in basket");
                addProduct(productInBascetDTO, numberOfOrderedProducts);
                return "catalogSuccess";
            }
        } catch (NoResultException exc) {
            logger.info("no such product in store");
            if (numberOfOrderedProducts > productInBascetDTO.getProduct().getQuantityInStore()) {
                logger.info("not enough products in store");
                return "catalogFalse";
            } else {
                logger.info("adding product in basket");
                addProduct(productInBascetDTO, numberOfOrderedProducts);
                return "catalogSuccess";
            }
        }
    }

    @Override
    @Transactional
    public void resetProductInBascetTable() {
        for (ProductInBascetEntity prod : productInBascetRepository.findAllProductsInBascetWithoutPages()) {
            productInBascetRepository.deleteProductInBascet(prod);
        }
    }

    @Override
    @Transactional
    public int getSummPriceForAllProducts(List<ProductInBascetDTO> productInBascetDTOList) {
        int summPrice = 0;
        for (ProductInBascetDTO prod : productInBascetDTOList) {
            summPrice += prod.getProduct().getPrice() * prod.getQuantity();
        }
        return summPrice;
    }


    @Override
    @Transactional
    public Map<String, String> addProductListToBasketAndReturnQuantityDifference(List<OrderedProductForHistoryDTO> productsToAdd) {

        Map<String, String> quantityDifference = new HashMap<>();
        ProductEntity productEntity;
        ProductInBascetDTO productInBascetDTO;

        for (OrderedProductForHistoryDTO orderedProd : productsToAdd) {
            productInBascetDTO = new ProductInBascetDTO();
            productEntity = productRepository.findProductsByName(orderedProd.getName());
            productInBascetDTO.setProduct(productEntity);
            String addResult = checkQuantityDifferenceThenAddProductInBascet(productInBascetDTO, orderedProd.getQuantity());
            if (addResult.equals("catalogFalse")) {
                if (productEntity.getQuantityInStore() != 0) {
                    checkQuantityDifferenceThenAddProductInBascet(productInBascetDTO, productEntity.getQuantityInStore());
                    quantityDifference.put(productEntity.getName(), productEntity.getQuantityInStore() + " / " + orderedProd.getQuantity());
                } else quantityDifference.put(productEntity.getName(), "0 / " + orderedProd.getQuantity());
            }

        }

        return quantityDifference;
    }
}
