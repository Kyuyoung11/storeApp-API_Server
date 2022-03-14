package com.example.storeapp.controller;


import com.example.storeapp.entity.Product;
import com.example.storeapp.entity.User;
import com.example.storeapp.service.ProductService;
import com.example.storeapp.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
URL: /api/cart

To get list of products:  GET "http://localhost:8080/api/products"
user_id로 cart에 있는 상품 목록 얻기 : GET "http://localhost:8080/api/cart/{id}"
*/


@RestController
@RequestMapping(path = "/api/cart")
public class CartController {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;


    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> retrieveCartProducts(@PathVariable Long id) {

        Optional<User> user = userService.findById(id);
        List<Product> list = new ArrayList<>();
        Iterable<Product> iterable = user.get().getProducts();
        for (Product p : iterable) {
            list.add(p);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
//        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }
}
