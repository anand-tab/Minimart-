package com.anand.productservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String prodName;
    @Enumerated(EnumType.STRING)
    private ProductType type = ProductType.VEGETABLE;
    private int price;
    private int quantity;
}
