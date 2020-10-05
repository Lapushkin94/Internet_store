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
    public List<ProductEntity> findAllExistingProducts(int page) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from ProductEntity").setFirstResult(10 * (page - 1)).setMaxResults(10).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ProductEntity> findAllProductsInStore(int page) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from ProductEntity WHERE quantityInStore!=0").setFirstResult(10 * (page - 1)).setMaxResults(10).list();
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

    @Override
    @SuppressWarnings("unchecked")
    public List<ProductEntity> findAllProductsByCategoryId(int categoryId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from ProductEntity productEn WHERE productEn.category.id =: categoryId").setParameter("categoryId", categoryId).list();
    }
}
