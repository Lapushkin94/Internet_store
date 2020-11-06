package com.mms.service.interfaces;

import com.mms.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllExistingProducts(int page);

    List<ProductDTO> getAllProductsInStoreNotNullQuantity(int page);

    void addProduct(ProductDTO productDTO);

    void deleteProduct(ProductDTO productDTO);

    void editProduct(ProductDTO productDTO);

    ProductDTO getProduct(int id);

    int getProductCount();

    int getProductCountNotNullQuantity();

    List<ProductDTO> getAllProductsByCategoryId(int categoryId);

    int getMaxProductPriceInStore();

    int getMinProductPriceInStore();

    List<ProductDTO> getAllProductsUsingFilter(int page, String inputProductName, Boolean isInStore, Integer minPrice, Integer maxPrice, String inputNameOfCategory);

    int getProductCountUsingFilter(String inputProductName, Boolean isInStore, Integer minPrice, Integer maxPrice, String inputNameOfCategory);

    String editProductAndReturnNameStatus(ProductDTO product);

    String addProductAndReturnUnicNameStatus(ProductDTO product);

}
