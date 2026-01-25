package com.anand.productservice.service;

import com.anand.productservice.dto.ProductMapper;
import com.anand.productservice.dto.ProductRequest;
import com.anand.productservice.dto.ProductResponse;
import com.anand.productservice.model.Product;
import com.anand.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;


    public  ProductResponse addProduct(ProductRequest productRequest) {
        Optional<Product> existingProductOpt = productRepository.findByProdName(productRequest.getProdName());

        Product productToSave;
        if(existingProductOpt.isPresent()){
            productToSave = existingProductOpt.get();
            productToSave.setQuantity(productToSave.getQuantity()+ productRequest.getQuantity());
            productToSave.setPrice(productRequest.getPrice());
        }
        else{
            productToSave =  productMapper.toEntity(productRequest);
        }
        Product saveProduct = productRepository.save(productToSave);
        return productMapper.toDto(saveProduct);
    }

    public  List<ProductResponse> getProducts() {

        List<Product> prod = productRepository.findAll();
        List<ProductResponse> productResponses = productMapper.toDto(prod);
        return productResponses;
    }

    public  ProductResponse getProdById(Long id) {
        Product prod = productRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
        return productMapper.toDto(prod);
    }

    public Boolean validateProduct(Long id) {
        return productRepository.existsById(id);
    }
}
