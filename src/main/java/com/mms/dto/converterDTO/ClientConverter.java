package com.mms.dto.converterDTO;

import com.mms.dto.ClientDTO;
import com.mms.model.ClientEntity;

public class ClientConverter {

    public static ClientEntity toEntity(ClientDTO clientDTO) {
        return ClientEntity.builder()
                .id(clientDTO.getId())
                .name(clientDTO.getName())
                .secondName(clientDTO.getSecondName())
                .birthday(clientDTO.getBirthday())
                .email(clientDTO.getEmail())
                .password(clientDTO.getPassword())
                .isActive(clientDTO.isActive())
                .clientAddress(clientDTO.getClientAddress())
                .orders(clientDTO.getOrders())
                .role(clientDTO.getRole())
                .build();
    }

    public static ClientDTO toDto(ClientEntity clientEntity) {
        return ClientDTO.builder()
                .id(clientEntity.getId())
                .name(clientEntity.getName())
                .secondName(clientEntity.getSecondName())
                .birthday(clientEntity.getBirthday())
                .email(clientEntity.getEmail())
                .password(clientEntity.getPassword())
                .isActive(clientEntity.isActive())
                .clientAddress(clientEntity.getClientAddress())
                .orders(clientEntity.getOrders())
                .role(clientEntity.getRole())
                .build();
    }
}
