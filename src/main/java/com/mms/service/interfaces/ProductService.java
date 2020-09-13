package com.mms.service.interfaces;

import com.mms.dto.ProductDTO;
import com.mms.model.ProductEntity;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllProducts(int page);
    void addProduct(ProductDTO productDTO);
    void deleteProduct(ProductDTO productDTO);
    void editProduct(ProductDTO productDTO);
    ProductDTO getProduct(int id);
    int getProductCount();

}
