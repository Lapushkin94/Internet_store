package com.mms.dto.converterDTO;

import com.mms.dto.ClientAddressDTO;
import com.mms.model.ClientAddressEntity;

public class ClientAddressConverter {

    public static ClientAddressEntity toEntity(ClientAddressDTO clientAddressDTO) {
        return ClientAddressEntity.builder()
                .id(clientAddressDTO.getId())
                .country(clientAddressDTO.getCountry())
                .city(clientAddressDTO.getCity())
                .postalCode(clientAddressDTO.getPostalCode())
                .street(clientAddressDTO.getStreet())
                .houseNumber(clientAddressDTO.getHouseNumber())
                .flatNumber(clientAddressDTO.getFlatNumber())
                .build();
    }

    public static ClientAddressDTO toDto(ClientAddressEntity clientAddressEntity) {
        return ClientAddressDTO.builder()
                .id(clientAddressEntity.getId())
                .country(clientAddressEntity.getCountry())
                .city(clientAddressEntity.getCity())
                .postalCode(clientAddressEntity.getPostalCode())
                .street(clientAddressEntity.getStreet())
                .houseNumber(clientAddressEntity.getHouseNumber())
                .flatNumber(clientAddressEntity.getFlatNumber())
                .build();
    }

}
