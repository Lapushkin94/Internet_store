package com.mms.repository.implementations;

import com.mms.model.ClientEntity;
import com.mms.repository.interfaces.ClientRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ClientEntity> findAllClients(int page) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from ClientEntity").setFirstResult(10 * (page - 1)).setMaxResults(10).list();
    }

    @Override
    public void saveClient(ClientEntity clientEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(clientEntity);
    }

    @Override
    public void deleteClient(ClientEntity clientEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(clientEntity);
    }

    @Override
    public void updateClient(ClientEntity clientEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(clientEntity);
    }

    @Override
    public ClientEntity findClientById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(ClientEntity.class, id);
    }

    @Override
    public int getClientCount() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count (*) from ClientEntity", Number.class).getSingleResult().intValue();
    }

}
