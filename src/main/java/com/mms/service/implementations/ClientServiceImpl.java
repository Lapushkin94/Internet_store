package com.mms.service.implementations;

import com.mms.dto.ClientDTO;
import com.mms.dto.converterDTO.ClientConverter;
import com.mms.repository.interfaces.ClientRepository;
import com.mms.service.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.mms.dto.converterDTO.ClientConverter.toDto;
import static com.mms.dto.converterDTO.ClientConverter.toEntity;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public List<ClientDTO> getAllClients(int page) {
        return clientRepository.findAllClients(page).stream()
                .map(ClientConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addClient(ClientDTO clientDTO) {
        clientRepository.saveClient(toEntity(clientDTO));
    }

    @Override
    @Transactional
    public void deleteClient(ClientDTO clientDTO) {
        clientRepository.deleteClient(toEntity(clientDTO));
    }

    @Override
    @Transactional
    public void editClient(ClientDTO clientDTO) {
        clientRepository.updateClient(toEntity(clientDTO));
    }

    @Override
    @Transactional
    public ClientDTO getClient(int id) {
        return toDto(clientRepository.findClientById(id));
    }

    @Override
    @Transactional
    public int getClientCount() {
        return clientRepository.getClientCount();
    }

}
