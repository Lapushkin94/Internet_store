package com.mms.repository.interfaces;

import com.mms.model.ClientEntity;

import java.util.List;

public interface ClientRepository {

    List<ClientEntity> findAllClients(int page);
    void saveClient(ClientEntity clientEntity);
    void deleteClient(ClientEntity clientEntity);
    void updateClient(ClientEntity clientEntity);
    ClientEntity findClientById(int id);
    int getClientCount();
    ClientEntity findByEmail(String inputEmail);

}
