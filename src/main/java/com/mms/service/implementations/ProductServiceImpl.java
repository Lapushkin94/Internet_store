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
    public List<ProductDTO> getAllProductsInStore(int page) {
        return productRepository.findAllProductsInStore(page).stream()
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
}
