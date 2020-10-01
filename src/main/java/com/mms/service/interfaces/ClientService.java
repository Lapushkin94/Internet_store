package com.mms.service.interfaces;

import com.mms.dto.ClientDTO;
import com.mms.dto.RoleDTO;

import java.util.List;

public interface ClientService {

    List<ClientDTO> getAllClients(int page);
    void addClient(ClientDTO clientDTO);
    void deleteClient(ClientDTO clientDTO);
    void editClient(ClientDTO clientDTO);
    ClientDTO getClient(int id);
    int getClientCount();
    ClientDTO getClientByEmail(String inputEmail);
    RoleDTO getRoleByRoleName(String roleName);
    List<RoleDTO> getAllRoles();
    List<String> getAllRoleNames();
}
