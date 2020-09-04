package com.mms.dao;

import com.mms.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MmsDAOImpl implements MmsDAO {

    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
    private static Map<Integer, Product> products = new HashMap<>();

    static {
        Product product1 = new Product("Axe", "Camping", "Fiskars", "Black", 2, 5, "Light axe, nice for campings", 8, 2000);
        products.put(product1.getId(), product1);
    }

    @Override
    public List<Product> allProducts() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void add(Product product) {
        product.setId(AUTO_ID.getAndIncrement());
        products.put(product.getId(), product);
    }

    @Override
    public void delete(Product product) {
        products.remove(product.getId());
    }

    @Override
    public void edit(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Product getById(int id) {
        return products.get(id);
    }
}
