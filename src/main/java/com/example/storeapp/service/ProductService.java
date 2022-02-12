package com.example.storeapp.service;

import com.example.storeapp.entity.Product;
import com.example.storeapp.entity.User;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAllProducts();
    Optional<Product> getProductById(Long id);
    List<Product> searchProducts();
}
