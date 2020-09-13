package com.mms.dto;

import com.mms.model.ClientEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class RoleDTO {

    private int id;
    private String name;
    private Set<ClientEntity> clients = new HashSet<>();



}

