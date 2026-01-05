package com.anand.orderservice.controller;

import com.anand.orderservice.dto.OrderRequest;
import com.anand.orderservice.dto.OrderResponse;
import com.anand.orderservice.service.OrderService;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest){
        return ResponseEntity.ok(orderService.createOrder(orderRequest)); 
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable @RequestBody Long id){
        return ResponseEntity.ok(orderService.deleteOrder(id));
    }
}
