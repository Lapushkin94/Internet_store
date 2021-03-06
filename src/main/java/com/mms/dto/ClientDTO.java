package com.mms.dto;

import com.mms.model.ClientAddressEntity;
import com.mms.model.OrderEntity;
import com.mms.model.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDTO {

    private int id;
    private String name;
    private String secondName;
    private String birthday;
    private String email;
    private String password;
    private boolean isActive;
    private ClientAddressEntity clientAddress;
    private Set<OrderEntity> orders = new HashSet<>();
    private RoleEntity role;

}
