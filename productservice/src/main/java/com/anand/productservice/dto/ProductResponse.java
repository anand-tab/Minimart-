package com.anand.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Long id;
    private String prodName;
    private String type;
    private int price;
    private int quantity;
}
