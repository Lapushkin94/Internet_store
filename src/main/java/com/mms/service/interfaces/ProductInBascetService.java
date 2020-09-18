package com.mms.service.interfaces;

import com.mms.dto.ProductInBascetDTO;

import java.util.List;

public interface ProductInBascetService {

    List<ProductInBascetDTO> getAllProductsInBascet(int page);
    List<ProductInBascetDTO> getAllProductsInBascetWithoutPages();
    void addProduct(ProductInBascetDTO productInBascetDTO, int numberOfOrderedProducts);
    void deleteProduct(ProductInBascetDTO productInBascetDTO);
    void editProduct(ProductInBascetDTO productInBascetDTO);
    ProductInBascetDTO getProduct(int id);
    int getProductCount();

}
