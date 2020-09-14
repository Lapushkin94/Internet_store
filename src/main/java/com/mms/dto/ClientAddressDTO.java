package com.mms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientAddressDTO {

    private int id;
    private String country;
    private String city;
    private int postalCode;
    private String street;
    private int houseNumber;
    private int flatNumber;

}
