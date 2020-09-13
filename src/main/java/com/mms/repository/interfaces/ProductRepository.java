package com.mms.repository.interfaces;

import com.mms.model.ProductEntity;

import java.util.List;

public interface ProductRepository {

    List<ProductEntity> findAllProducts(int page);
    void saveProduct(ProductEntity productEntity);
    void deleteProduct(ProductEntity productEntity);
    void updateProduct(ProductEntity productEntity);
    ProductEntity findProductById(int id);
    int getProductCount();

}
