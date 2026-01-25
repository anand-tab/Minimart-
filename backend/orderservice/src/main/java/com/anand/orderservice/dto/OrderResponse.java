package com.anand.orderservice.dto;

import com.anand.orderservice.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private Long orderId;
    private String userId;
    private Long productId;
    private int quantity;
    private LocalDate createdAt;
    private OrderStatus status;
}
