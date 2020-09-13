package com.mms.repository.implementations;

import com.mms.model.OrderEntity;
import com.mms.repository.interfaces.OrderRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OrderEntity> findAllOrders(int page) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from OrderEntity ").setFirstResult(10 * (page - 1)).setMaxResults(10).list();
    }

    @Override
    public void saveOrder(OrderEntity orderEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(orderEntity);
    }

    @Override
    public void deleteOrder(OrderEntity orderEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(orderEntity);
    }

    @Override
    public void updateOrder(OrderEntity orderEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(orderEntity);
    }

    @Override
    public OrderEntity findOrderById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(OrderEntity.class, id);
    }

    @Override
    public int getOrderCount() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count (*) from OrderEntity", Number.class).getSingleResult().intValue();
    }
}
