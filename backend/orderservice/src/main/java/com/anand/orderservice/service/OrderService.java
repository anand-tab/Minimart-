package com.anand.orderservice.service;

import com.anand.orderservice.client.ProductClient;
import com.anand.orderservice.client.UserClient;
import com.anand.orderservice.dto.OrderMapper;
import com.anand.orderservice.dto.OrderRequest;
import com.anand.orderservice.dto.OrderResponse;
import com.anand.orderservice.model.Order;
import com.anand.orderservice.model.OrderStatus;
import com.anand.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final UserClient userClient;

    public OrderResponse createOrder(OrderRequest orderRequest) {
        log.info("Checking user is valid or not");
        if(!userClient.validateUser(orderRequest.getUserId())){
           throw new IllegalArgumentException("User is not valid ");
       }
        log.info("Checking product is valid or not");
        if(!productClient.validateProduct(Long.valueOf(orderRequest.getProductId()))){
            throw new IllegalArgumentException("Product entered is not valid");
        }
        log.info("Everything seems fine");

        Order order = Order.builder()
                .userId(orderRequest.getUserId())
                .productId(orderRequest.getProductId())
                .quantity(orderRequest.getQuantity())
                .build();

        // 4️⃣ Save Order
        order.setStatus(OrderStatus.Created);
        Order savedOrder = orderRepository.save(order);

        // 5️⃣ Build Response
        return OrderResponse.builder()
                .orderId(savedOrder.getOrderId())
                .userId(savedOrder.getUserId())
                .productId(savedOrder.getProductId())
                .quantity(savedOrder.getQuantity())
                .createdAt(savedOrder.getCreatedAt())
                .status(savedOrder.getStatus())
                .build();

    }

    public String deleteOrder(Long id) {
        orderRepository.deleteById(id);
        return "Deleted for Id "+ id;
    }
}
