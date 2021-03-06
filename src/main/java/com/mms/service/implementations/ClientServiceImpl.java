package com.mms.service.implementations;

import com.mms.dto.ClientDTO;
import com.mms.dto.OrderDTO;
import com.mms.dto.RoleDTO;
import com.mms.dto.converterDTO.ClientConverter;
import com.mms.dto.converterDTO.OrderConverter;
import com.mms.dto.converterDTO.RoleConverter;
import com.mms.model.ClientEntity;
import com.mms.repository.interfaces.ClientRepository;
import com.mms.service.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.mms.dto.converterDTO.ClientConverter.toDto;
import static com.mms.dto.converterDTO.ClientConverter.toEntity;

@Service
public class ClientServiceImpl implements ClientService {

    private static final Logger logger = Logger.getLogger(ClientServiceImpl.class.getName());

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
        logger.info("adding client");
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
            logger.info("getting client by email " + inputEmail);
            clientDTO = ClientConverter.toDto(clientRepository.findByEmail(inputEmail));
        } catch (NoResultException exc) {
            logger.info("cant find client by email " + inputEmail);
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
        return clientRepository.findOrderListByClientId(clientId, page).stream()
                .map(OrderConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public String checkExistingEmailsThenAddAnotherOneAndReturnStatus(ClientDTO client) {

//        try {
//            ClientEntity client = clientRepository.findByEmail(clientDTO.getEmail());
//            if (client.getId() == clientDTO.getId()) {
//                throw new NoResultException();
//            }
//            logger.info("editing fail");
//            return "redirect:/myProfile/editProfile/?emailStatus=1";
//        } catch (NoResultException exc) {
//            editClient(clientDTO);
//            return "okStatus";
//        }
        try {
            ClientDTO clientDTO = ClientConverter.toDto(clientRepository.findByEmail(client.getEmail()));
            if (client.getId() == clientDTO.getId()) {
                ClientEntity clientToUpdate = clientRepository.findClientById(client.getId());
                clientToUpdate.setName(client.getName());
                clientToUpdate.setSecondName(client.getSecondName());
                clientToUpdate.setBirthday(client.getBirthday());
                clientToUpdate.setEmail(client.getEmail());
                clientRepository.updateClient(clientToUpdate);
            }
            else {
                logger.info("fail editing client");
                return "redirect:/myProfile/editProfile/?emailStatus=1";
            }
        }
        catch (NoResultException exc) {
            logger.info("editing client");
            clientRepository.updateClient(ClientConverter.toEntity(client));
        }
        return "okStatus";
    }

    @Override
    @Transactional
    public String addClientAndReturnUniqEmailStatus(ClientDTO clientDTO) {

        try {
            ClientEntity client = clientRepository.findByEmail(clientDTO.getEmail());
            logger.info("fail adding client");
            return "redirect:/signUpPage/?emailStatus=1";
        } catch (NoResultException exc) {
            logger.info("adding client " + clientDTO.getId());
            clientDTO.setPassword(passwordEncoder.encode(clientDTO.getPassword()));
            clientRepository.saveClient(ClientConverter.toEntity(clientDTO));
        }

        return "okStatus";
    }
}
