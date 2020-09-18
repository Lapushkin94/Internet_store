package com.mms.service.implementations;

import com.mms.dto.ProductInBascetDTO;
import com.mms.dto.converterDTO.ProductInBascetConverter;
import com.mms.repository.interfaces.ProductInBascetRepository;
import com.mms.service.interfaces.ProductInBascetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

//        getAllProductsInBascetWithoutPages().stream()
//                .filter(product -> product.getProduct().getId() == productInBascetDTO.getProduct().getId())
//                .forEach(product -> {
//                    product.setQuantity(product.getQuantity() + numberOfOrderedProducts);
//                    editProduct(product);
//                });
        productInBascetRepository.saveProduct(toEntity(productInBascetDTO));
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
}
