package com.mms.dto.converter;

import com.mms.dto.RoleDTO;
import com.mms.model.RoleEntity;

public class RoleConverter {

    public static RoleEntity toEntity(RoleDTO roleDTO) {
        return RoleEntity.builder()
                .id(roleDTO.getId())
                .name(roleDTO.getName())
                .clients(roleDTO.getClients())
                .build();
    }

    public static RoleDTO toDto(RoleEntity roleEntity) {
        return RoleDTO.builder()
                .id(roleEntity.getId())
                .name(roleEntity.getName())
                .clients(roleEntity.getClients())
                .build();
    }

}