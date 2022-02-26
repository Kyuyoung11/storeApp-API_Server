package com.example.storeapp.controller;



//import com.example.storeapp.assembler.ProductModelAssembler;
import com.example.storeapp.entity.Product;
import com.example.storeapp.repository.ProductRepository;
import com.example.storeapp.service.ProductService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



/*
URL: /api/product

To get list of products:  GET "http://localhost:8080/api/products"
To get products info by id:  GET "http://localhost:8080/api/products/get/{id}"
To search products by words: GET "http://localhost:8080/api/products/search/{words}"
*/

@RestController
@RequestMapping(path = "/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
//    @Autowired
//    private ProductModelAssembler productModelAssembler;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Product>> retrieveAllProducts() {

        List<Product> list = new ArrayList<>();
        Iterable<Product> iterable = productService.getAllProducts();
        for (Product p : iterable) {
            list.add(p);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> retrieveProduct(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }



    @RequestMapping(path = "/search", method = RequestMethod.POST)
    public ResponseEntity<?> searchProducts(@RequestBody @Valid StringDto request) {

        // Getting all products in application...
        List<Product> list = new ArrayList<>();
        Iterable<Product> iterable = productService.searchProducts(request.getStr());
        for (Product p : iterable) {
            list.add(p);
        }

        return ResponseEntity.ok(list);
    }






    @Getter
    @Setter
    static class StringDto {
        @NotNull(message = "string is required")
        @Size(message = "string must be equal to or lower than 300", min = 1, max = 300)
        private String str;
    }
}

