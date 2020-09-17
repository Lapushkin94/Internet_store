package com.mms.repository.implementations;

import com.mms.model.OrderStatusEntity;
import com.mms.repository.interfaces.OrderStatusRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderStatusRepositoryImpl implements OrderStatusRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public OrderStatusEntity findOpenedStatus() {
        Session session = sessionFactory.getCurrentSession();
        return session.get(OrderStatusEntity.class, 1);
    }

    @Override
    public OrderStatusEntity findInProcessStatus() {
        Session session = sessionFactory.getCurrentSession();
        return session.get(OrderStatusEntity.class, 2);
    }

    @Override
    public OrderStatusEntity findClosedStatus() {
        Session session = sessionFactory.getCurrentSession();
        return session.get(OrderStatusEntity.class, 3);
    }
}
