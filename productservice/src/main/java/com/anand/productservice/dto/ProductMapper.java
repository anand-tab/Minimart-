package com.anand.productservice.dto;


import com.anand.productservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductRequest productRequest);

    ProductResponse toDto(Product product);

    List<ProductResponse> toDto(List<Product> products);
}
