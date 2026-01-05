package com.anand.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "product-service" , url = "http://localhost:8082")
public interface ProductClient {

    @GetMapping("/api/product/validate/{id}")
    boolean validateProduct(@PathVariable("id") Long id);
}
