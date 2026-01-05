package com.anand.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Data
@Table(name="orders")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String userId;
    private Long productId;
    private int quantity;

    @CreationTimestamp
    private LocalDate createdAt;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.Created;
}
