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

    @Override
    @SuppressWarnings("unchecked")
    public List<OrderedProductForHistoryEntity> findAllProductsInHistoryByClientEmail(String clientEmail) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from OrderedProductForHistoryEntity productInHistory " +
                "WHERE productInHistory.orderInHistory.client.email =: clientEmail")
                .setParameter("clientEmail", clientEmail)
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OrderedProductForHistoryEntity> findAllProductsInHistoryByOrderId(int inputOrderId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from OrderedProductForHistoryEntity orderedProductFromHistory " +
                "where orderedProductFromHistory.orderInHistory.id =: inputOrderId")
                .setParameter("inputOrderId", inputOrderId)
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> getTop10ProductsByPurchaseNumber() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select name, sum(quantity) as sumQuan " +
                "from OrderedProductForHistoryEntity group by name order by sumQuan desc")
                .setMaxResults(10)
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> getTop10ClientsByPurchase() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select prod.orderInHistory.client.email as email, sum(price * quantity) as profit " +
                "from OrderedProductForHistoryEntity prod group by email order by profit desc")
                .setMaxResults(10)
                .list();
    }

    @Override
    public int getProfitByDate(String currentDateMinusNumberOfDays) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select sum(price * quantity) as profit " +
                "from OrderedProductForHistoryEntity prod where prod.orderInHistory.date > :currentDateMinusNumberOfDays", Number.class)
                .setParameter("currentDateMinusNumberOfDays", currentDateMinusNumberOfDays)
                .getSingleResult()
                .intValue();
    }

}
