package com.anand.productservice.controller;

import com.anand.productservice.dto.ProductRequest;
import com.anand.productservice.dto.ProductResponse;
import com.anand.productservice.model.Product;
import com.anand.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;


    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest){
        return ResponseEntity.ok(productService.addProduct(productRequest));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getproducts(){
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProdById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProdById(id));
    }

    @GetMapping("/validate/{id}")
    public ResponseEntity<Boolean> validateProduct(@PathVariable Long id){

        return ResponseEntity.ok(productService.validateProduct(id));
    }
}
