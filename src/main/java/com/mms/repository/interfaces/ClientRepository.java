package com.mms.repository.interfaces;

import com.mms.model.ClientEntity;
import com.mms.model.RoleEntity;

import java.util.List;

public interface ClientRepository {

    List<ClientEntity> findAllClients(int page, RoleEntity roleEntity);
    void saveClient(ClientEntity clientEntity);
    void deleteClient(ClientEntity clientEntity);
    void updateClient(ClientEntity clientEntity);
    ClientEntity findClientById(int id);
    int getClientCount(RoleEntity roleEntity);
    ClientEntity findByEmail(String inputEmail);
    RoleEntity findRoleByRoleName(String roleName);
    List<RoleEntity> findAllRoles();
}
