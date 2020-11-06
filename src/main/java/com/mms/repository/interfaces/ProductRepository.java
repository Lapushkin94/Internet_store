package com.mms.repository.interfaces;

import com.mms.model.ProductEntity;

import java.util.List;

public interface ProductRepository {

    List<ProductEntity> findAllExistingProducts(int page);

    List<ProductEntity> findAllProductsInStoreNotNullQuantity(int page);

    void saveProduct(ProductEntity productEntity);

    void deleteProduct(ProductEntity productEntity);

    void updateProduct(ProductEntity productEntity);

    ProductEntity findProductById(int id);

    //    int getProductQuantityByProductId(int id);
    ProductEntity findProductByIdTransactional(int id);

    int getProductCount();

    int getProductCountNotNullQuantity();

    List<ProductEntity> findAllProductsByCategoryId(int categoryId);

    int getMaxProductPriceInStore();

    int getMinProductPriceInStore();

    List<ProductEntity> findAllProductsUsingFilter(int page, String inputProductName, Boolean isInStore, Integer minPrice, Integer maxPrice, String inputNameOfCategory);

    List<ProductEntity> findAllProductsUsingFilterWithoutPages(String inputProductName, Boolean isInStore, Integer minPrice, Integer maxPrice, String inputNameOfCategory);

    ProductEntity findProductsByName(String productName);

}
