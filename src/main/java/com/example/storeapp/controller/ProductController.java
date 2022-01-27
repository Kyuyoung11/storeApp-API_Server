package com.example.storeapp.controller;



import com.example.storeapp.assembler.ProductModelAssembler;
import com.example.storeapp.entity.Product;
import com.example.storeapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



/*
URL: /api/product

To get list of products:  GET "http://localhost:8080/api/products"
To get products info by id:  GET "http://localhost:8080/api/products/{id}"
*/

@RestController
@RequestMapping(path = "/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductModelAssembler productModelAssembler;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> retrieveAllProducts() {

        // Getting all products in application...
        final List<Product> products = productService.getAllProducts();

        return ResponseEntity.ok(
                productModelAssembler.toCollectionModel(products));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> retrieveProduct(@PathVariable Long id) {

        return productService.getProductById(id)
                .map(productModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
