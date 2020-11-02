package com.mms.repository.implementations;

import com.mms.model.OrderEntity;
import com.mms.model.OrderedProductForHistoryEntity;
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
        return session.createQuery("from OrderEntity order by date desc ").setFirstResult(10 * (page - 1)).setMaxResults(10).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OrderEntity> findAllOrdersWithoutPages() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from OrderEntity ").list();
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

    @Override
    public int saveOrderAndReturnId(OrderEntity orderEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(orderEntity);
        session.flush();
        return orderEntity.getId();
    }

    @Override
    public int getOrderCountByClientId(int inputClientId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count (*) from OrderEntity WHERE client.id =: inputClientId", Number.class).setParameter("inputClientId", inputClientId).getSingleResult().intValue();
    }

    @Override
    public int getProductsCountByOrdersId(int orderId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count (*) from OrderedProductForHistoryEntity WHERE orderInHistory.id =: orderId", Number.class).setParameter("orderId", orderId).getSingleResult().intValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OrderedProductForHistoryEntity> findOrdersProductHistoryByOrderId(int orderId, int orderHistoryPage) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from OrderedProductForHistoryEntity orderedProductForHistory WHERE orderedProductForHistory.orderInHistory.id =: orderId").setParameter("orderId", orderId).setFirstResult(10 * (orderHistoryPage - 1)).setMaxResults(10).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OrderedProductForHistoryEntity> findOrdersProductHistoryByOrderIdWithoutPages(int orderId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from OrderedProductForHistoryEntity orderedProductForHistory WHERE orderedProductForHistory.orderInHistory.id =: orderId").setParameter("orderId", orderId).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OrderEntity> findListOrdersByNumberOfDays(String currentDateMinusNumberOfDays) {

        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("from OrderEntity myOrder where myOrder.date > :currentDateMinusNumberOfDays").setParameter("currentDateMinusNumberOfDays", currentDateMinusNumberOfDays).list();
    }

}
