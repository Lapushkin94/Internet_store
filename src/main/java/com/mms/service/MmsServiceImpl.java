package com.mms.service;

import com.mms.dao.MmsDAO;
import com.mms.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MmsServiceImpl implements MmsService {

    private MmsDAO mmsDAO;

    @Autowired
    public void setMmsDAO(MmsDAO mmsDAO) {
        this.mmsDAO = mmsDAO;
    }

    @Override
    @Transactional
    public List<Product> allProducts() {
        return mmsDAO.allProducts();
    }

    @Override
    @Transactional
    public void add(Product product) {
        mmsDAO.add(product);
    }

    @Override
    @Transactional
    public void delete(Product product) {
        mmsDAO.delete(product);
    }

    @Override
    @Transactional
    public void edit(Product product) {
        mmsDAO.edit(product);
    }

    @Override
    @Transactional
    public Product getById(int id) {
        return mmsDAO.getById(id);
    }
}
