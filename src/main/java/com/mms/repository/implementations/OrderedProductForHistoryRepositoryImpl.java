package com.mms.repository.implementations;


import com.mms.model.OrderedProductForHistoryEntity;
import com.mms.repository.interfaces.OrderedProductForHistoryRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderedProductForHistoryRepositoryImpl implements OrderedProductForHistoryRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OrderedProductForHistoryEntity> findAllProductsWithPages(int page) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from OrderedProductForHistoryEntity").setFirstResult(10 * (page - 1)).setMaxResults(10).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OrderedProductForHistoryEntity> findAllProductsWithoutPages() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from OrderedProductForHistoryEntity").list();
    }

    @Override
    public void saveProduct(OrderedProductForHistoryEntity orderedProductForHistoryEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(orderedProductForHistoryEntity);
    }

    @Override
    public int getProductCount() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count (*) from OrderedProductForHistoryEntity", Number.class).getSingleResult().intValue();
    }
}
