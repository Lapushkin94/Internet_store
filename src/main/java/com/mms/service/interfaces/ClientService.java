package com.mms.service.interfaces;

import com.mms.dto.ClientDTO;

import java.util.List;

public interface ClientService {

    List<ClientDTO> getAllClients(int page);
    void addClient(ClientDTO clientDTO);
    void deleteClient(ClientDTO clientDTO);
    void editClient(ClientDTO clientDTO);
    ClientDTO getClient(int id);
    int getClientCount();
    ClientDTO getClientByEmail(String inputEmail);

}
