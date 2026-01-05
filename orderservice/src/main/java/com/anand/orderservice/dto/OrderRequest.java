package com.anand.orderservice.dto;

import com.anand.orderservice.model.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private String userId;
    private Long productId;
    private int quantity;
}
