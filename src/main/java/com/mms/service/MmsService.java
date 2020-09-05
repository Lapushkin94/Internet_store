package com.mms.service;

import com.mms.model.Product;

import java.util.List;

public interface MmsService {

    List<Product> allProducts(int page);
    void add(Product product);
    void delete(Product product);
    void edit(Product product);
    Product getById(int id);
    int productsCount();


}
