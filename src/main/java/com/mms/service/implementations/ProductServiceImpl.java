package com.mms.service.implementations;

import com.mms.repository.interfaces.ProductRepository;
import com.mms.dto.ProductDTO;
import com.mms.dto.converterDTO.ProductConverter;
import com.mms.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.mms.dto.converterDTO.ProductConverter.toDto;
import static com.mms.dto.converterDTO.ProductConverter.toEntity;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public List<ProductDTO> getAllExistingProducts(int page) {
        return productRepository.findAllExistingProducts(page).stream()
                .map(ProductConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ProductDTO> getAllProductsInStoreNotNullQuantity(int page) {
        return productRepository.findAllProductsInStoreNotNullQuantity(page).stream()
                .map(ProductConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int getProductCount() {
        return productRepository.getProductCount();
    }

    @Override
    @Transactional
    public int getProductCountNotNullQuantity() {
        return productRepository.getProductCountNotNullQuantity();
    }

    @Override
    @Transactional
    public void addProduct(ProductDTO productDTO) {
        productRepository.saveProduct(toEntity(productDTO));
    }

    @Override
    @Transactional
    public void deleteProduct(ProductDTO productDTO) {
        productRepository.deleteProduct(toEntity(productDTO));
    }

    @Override
    @Transactional
    public void editProduct(ProductDTO productDTO) {
        productRepository.updateProduct(toEntity(productDTO));
    }

    @Override
    @Transactional
    public ProductDTO getProduct(int id) {
        return toDto(productRepository.findProductById(id));
    }

    @Override
    @Transactional
    public List<ProductDTO> getAllProductsByCategoryId(int categoryId) {
        return productRepository.findAllProductsByCategoryId(categoryId).stream()
                .map(ProductConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int getMaxProductPriceInStore() {
        return productRepository.getMaxProductPriceInStore();
    }

    @Override
    @Transactional
    public int getMinProductPriceInStore() {
        return productRepository.getMinProductPriceInStore();
    }

    @Override
    @Transactional
    public List<ProductDTO> getAllProductsUsingFilter(int page, String inputProductName, Boolean isInStore, Integer minPrice, Integer maxPrice, String inputNameOfCategory) {
        return productRepository.findAllProductsUsingFilter(page, inputProductName, isInStore, minPrice, maxPrice, inputNameOfCategory).stream()
                .map(ProductConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int getProductCountUsingFilter(String inputProductName, Boolean isInStore, Integer minPrice, Integer maxPrice, String inputNameOfCategory) {
        return productRepository.findAllProductsUsingFilterWithoutPages(inputProductName, isInStore, minPrice, maxPrice, inputNameOfCategory).size();
    }

}
