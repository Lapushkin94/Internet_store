package com.mms.repository.implementations;

import com.mms.model.OrderStatusEntity;
import com.mms.repository.interfaces.OrderStatusRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    @SuppressWarnings("unchecked")
    public List<OrderStatusEntity> findAllOrderStatus() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from OrderStatusEntity").list();
    }

    @Override
    public OrderStatusEntity getOrderStatusByName(String statusName) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM OrderStatusEntity statusEntity WHERE statusEntity.name =: statusName", OrderStatusEntity.class).setParameter("statusName", statusName).getSingleResult();
    }
}
