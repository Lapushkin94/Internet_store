package com.mms.model;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date")
    private String date;
    @Column(name = "pay_status")
    private String payStatus;
    @Column(name = "comment")
    private String comment;
    @ManyToOne
    @JoinColumn(name = "order_status_id")
    private OrderStatusEntity orderStatus;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ProductInBascetEntity> productInBascets = new HashSet<>();


}
