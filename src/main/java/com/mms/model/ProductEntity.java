package com.mms.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class ProductEntity {

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
    @Column(name = "quantity_in_store")
    private int quantityInStore;
    @Column(name = "color")
    private String color;
    @Column(name = "weight")
    private int weight;
    @Column(name = "country")
    private String country;
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;


}
