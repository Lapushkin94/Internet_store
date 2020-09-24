package com.mms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ordered_products_history_by_order_id")
public class OrderedProductForHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "alternative_name")
    private String alternative_name;
    @Column(name = "brandName")
    private String brandName;
    @Column(name = "price")
    private int price;
    @Column(name = "color")
    private String color;
    @Column(name = "weight")
    private int weight;
    @Column(name = "country")
    private String country;
    @Column(name = "description")
    private String description;
    @Column(name = "quantity")
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "parent_order_id")
    private OrderEntity orderInHistory;

}
