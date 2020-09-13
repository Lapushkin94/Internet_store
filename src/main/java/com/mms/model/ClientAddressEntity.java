package com.mms.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "client_address")
public class ClientAddressEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "postalCode")
    private int postalCode;
    @Column(name = "street")
    private String street;
    @Column(name = "houseNumber")
    private int houseNumber;
    @Column(name = "flatNumber")
    private int flatNumber;


}
