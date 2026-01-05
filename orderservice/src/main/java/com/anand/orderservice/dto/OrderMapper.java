package com.anand.orderservice.dto;


import com.anand.orderservice.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(OrderRequest orderRequest);

    OrderResponse toDto(Order order);
}
