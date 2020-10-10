package com.mms.service.implementations;

import com.mms.dto.ClientDTO;
import com.mms.dto.OrderDTO;
import com.mms.dto.RoleDTO;
import com.mms.dto.converterDTO.ClientConverter;
import com.mms.dto.converterDTO.OrderConverter;
import com.mms.dto.converterDTO.RoleConverter;
import com.mms.repository.interfaces.ClientRepository;
import com.mms.service.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.mms.dto.converterDTO.ClientConverter.toDto;
import static com.mms.dto.converterDTO.ClientConverter.toEntity;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public List<ClientDTO> getAllClients(int page, String clientRole) {
        return clientRepository.findAllClients(page, RoleConverter.toEntity(getRoleByRoleName(clientRole))).stream()
                .map(ClientConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addClient(ClientDTO clientDTO) {
        clientDTO.setPassword(passwordEncoder.encode(clientDTO.getPassword()));
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
    public int getClientCount(String clientRole) {
        return clientRepository.getClientCount(RoleConverter.toEntity(getRoleByRoleName(clientRole)));
    }

    @Override
    @Transactional
    public ClientDTO getClientByEmail(String inputEmail) {
        ClientDTO clientDTO;
        try {
            clientDTO = ClientConverter.toDto(clientRepository.findByEmail(inputEmail));
        }
        catch (NoResultException exc) {
            throw new UsernameNotFoundException("UserNotFound");
        }
        return clientDTO;
    }

    @Override
    @Transactional
    public RoleDTO getRoleByRoleName(String roleName) {
        return RoleConverter.toDto(clientRepository.findRoleByRoleName(roleName));
    }

    @Override
    @Transactional
    public List<RoleDTO> getAllRoles() {
        return clientRepository.findAllRoles().stream()
                .map(RoleConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<String> getAllRoleNames() {
        List<String> roleNamesList = new ArrayList<>();
        List<RoleDTO> roleDTOList = getAllRoles();
        for (RoleDTO s : roleDTOList) {
            roleNamesList.add(s.getName());
        }
        return roleNamesList;
    }

    @Override
    @Transactional
    public List<OrderDTO> getOrderListByClientId(int clientId, int page) {
        List<OrderDTO> orderDTOList = clientRepository.findOrderListByClientId(clientId, page).stream()
                .map(OrderConverter::toDto)
                .collect(Collectors.toList());
        Collections.reverse(orderDTOList);
        return orderDTOList;
    }

}
