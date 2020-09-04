package com.mms.service;

import com.mms.dao.MmsDAO;
import com.mms.dao.MmsDAOImpl;
import com.mms.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MmsServiceImpl implements MmsService {

    private MmsDAO mmsDAO;

    @Autowired
    public void setMmsDAO(MmsDAO mmsDAO) {
        this.mmsDAO = mmsDAO;
    }

    @Override
    public List<Product> allProducts() {
        return mmsDAO.allProducts();
    }

    @Override
    public void add(Product product) {
        mmsDAO.add(product);
    }

    @Override
    public void delete(Product product) {
        mmsDAO.delete(product);
    }

    @Override
    public void edit(Product product) {
        mmsDAO.edit(product);
    }

    @Override
    public Product getById(int id) {
        return mmsDAO.getById(id);
    }
}
