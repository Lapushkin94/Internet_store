package com.mms.repository.implementations;

import com.mms.model.ProductEntity;
import com.mms.repository.interfaces.ProductRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ProductEntity> findAllProducts(int page) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from ProductEntity").setFirstResult(10 * (page - 1)).setMaxResults(10).list();
    }

    @Override
    public void saveProduct(ProductEntity productEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(productEntity);
    }

    @Override
    public void deleteProduct(ProductEntity productEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(productEntity);
    }

    @Override
    public void updateProduct(ProductEntity productEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(productEntity);
    }

    @Override
    public ProductEntity findProductById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(ProductEntity.class, id);
    }

    @Override
    public int getProductCount() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count (*) from ProductEntity", Number.class).getSingleResult().intValue();
    }
}
