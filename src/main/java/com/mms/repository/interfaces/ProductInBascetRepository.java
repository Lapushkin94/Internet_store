package com.mms.repository.interfaces;

import com.mms.model.ProductInBascetEntity;

import java.util.List;

public interface ProductInBascetRepository {

    List<ProductInBascetEntity> findAllProductsInBascet(int page);

    List<ProductInBascetEntity> findAllProductsInBascetWithoutPages();

    void saveProduct(ProductInBascetEntity productInBascetEntity);

    void deleteProductInBascet(ProductInBascetEntity productInBascetEntity);

    void updateProduct(ProductInBascetEntity productInBascetEntity);

    ProductInBascetEntity findProductInBascetById(int id);

    int getProductInBascetCount();

    ProductInBascetEntity findProductInBascetByProductId(int productId);

}
