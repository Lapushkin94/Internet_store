package com.mms.repository.implementations;

import com.mms.model.ClientEntity;
import com.mms.model.RoleEntity;
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
    public List<ClientEntity> findAllClients(int page, RoleEntity roleEntity) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from ClientEntity clientEntity WHERE clientEntity.role =: roleEntity").setParameter("roleEntity", roleEntity).setFirstResult(10 * (page - 1)).setMaxResults(10).list();
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
    public int getClientCount(RoleEntity roleEntity) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count (*) from ClientEntity WHERE role=: roleEntity", Number.class).setParameter("roleEntity", roleEntity).getSingleResult().intValue();
    }

    @Override
    public ClientEntity findByEmail(String inputEmail) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from ClientEntity client WHERE client.email =: inputEmail", ClientEntity.class)
                .setParameter("inputEmail", inputEmail)
                .getSingleResult();
    }

    @Override
    public RoleEntity findRoleByRoleName(String roleName) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from RoleEntity role WHERE role.name =: roleName", RoleEntity.class)
                .setParameter("roleName", roleName)
                .getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<RoleEntity> findAllRoles() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from RoleEntity").list();
    }
}
