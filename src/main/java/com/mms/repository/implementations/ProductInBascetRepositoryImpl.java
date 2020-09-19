package com.mms.repository.implementations;

import com.mms.model.ProductInBascetEntity;
import com.mms.repository.interfaces.ProductInBascetRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductInBascetRepositoryImpl implements ProductInBascetRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ProductInBascetEntity> findAllProductsInBascet(int page) {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("from ProductInBascetEntity")
                .setFirstResult(10 * (page - 1))
                .setMaxResults(10)
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ProductInBascetEntity> findAllProductsInBascetWithoutPages() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from ProductInBascetEntity").list();
    }

    @Override
    public int getProductInBascetCount() {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select count(*) from ProductInBascetEntity", Number.class)
                .getSingleResult()
                .intValue();
    }

    @Override
    public void saveProduct(ProductInBascetEntity productInBascetEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(productInBascetEntity);
    }

    @Override
    public void deleteProductInBascet(ProductInBascetEntity productInBascetEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(productInBascetEntity);
    }

    @Override
    public void updateProduct(ProductInBascetEntity productInBascetEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(productInBascetEntity);
    }

    @Override
    public ProductInBascetEntity findProductInBascetById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(ProductInBascetEntity.class, id);
    }

    @Override
    public ProductInBascetEntity findProductInBascetByProductId(int productId) {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("from ProductInBascetEntity prod WHERE prod.id =: productId", ProductInBascetEntity.class)
                .setParameter("productId", productId)
                .getSingleResult();
    }

}
