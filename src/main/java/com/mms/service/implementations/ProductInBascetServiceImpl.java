package com.mms.service.implementations;

import com.mms.dto.OrderedProductForHistoryDTO;
import com.mms.dto.ProductInBascetDTO;
import com.mms.dto.converterDTO.ProductInBascetConverter;
import com.mms.model.ProductInBascetEntity;
import com.mms.repository.interfaces.ProductInBascetRepository;
import com.mms.service.interfaces.ProductInBascetService;
import com.mms.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

import static com.mms.dto.converterDTO.ProductInBascetConverter.toDto;
import static com.mms.dto.converterDTO.ProductInBascetConverter.toEntity;

@Service
public class ProductInBascetServiceImpl implements ProductInBascetService {

    private ProductInBascetRepository productInBascetRepository;

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
        // Заменить на if - else
        productInBascetDTO.setQuantity(numberOfOrderedProducts);
        try {
            ProductInBascetEntity productInBascetEntity = productInBascetRepository.findProductInBascetByProductId(productInBascetDTO.getProduct().getId());
            productInBascetEntity.setQuantity(productInBascetEntity.getQuantity() + numberOfOrderedProducts);
            productInBascetRepository.updateProduct(productInBascetEntity);
        } catch (NoResultException exc) {
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

        try {
            if ((productInBascetRepository
                    .findProductInBascetByProductId(productInBascetDTO.getProduct().getId())
                    .getQuantity() + numberOfOrderedProducts) > productInBascetDTO.getProduct().getQuantityInStore()) {
                return "catalogFalse";
            } else {
                addProduct(productInBascetDTO, numberOfOrderedProducts);
                return "catalogSuccess";
            }
        }
        catch (NoResultException exc) {
            if (numberOfOrderedProducts > productInBascetDTO.getProduct().getQuantityInStore()) {
                return "catalogFalse";
            }
            else {
                addProduct(productInBascetDTO, numberOfOrderedProducts);
                return "catalogSuccess";
            }
        }
    }

    @Override
    @Transactional
    public void resetProductInBascetTable() {
        List<ProductInBascetEntity> productInBascetEntities = productInBascetRepository.findAllProductsInBascetWithoutPages();
        for (ProductInBascetEntity prod : productInBascetEntities) {
            productInBascetRepository.deleteProductInBascet(prod);
        }
    }

    @Override
    @Transactional
    public int getSummPriceForAllProducts(List<ProductInBascetDTO> productInBascetDTOList) {
        int summPrice = 0;
        for(ProductInBascetDTO prod : productInBascetDTOList) {
            summPrice += prod.getProduct().getPrice() * prod.getQuantity();
        }
        return summPrice;
    }

    @Override
    @Transactional
    public void clearBasket() {
        for(ProductInBascetEntity prod : productInBascetRepository.findAllProductsInBascetWithoutPages()) {
            productInBascetRepository.deleteProductInBascet(prod);
        }
    }



}
