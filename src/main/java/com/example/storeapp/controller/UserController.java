package com.example.storeapp.controller;


import com.example.storeapp.assembler.UserModelAssembler;
import com.example.storeapp.entity.User;
import com.example.storeapp.repository.UserRepository;
import com.example.storeapp.representationmodel.UserModel;
import com.example.storeapp.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



/*
URL: /api/users

To get list of products:  GET "http://localhost:8080/api/users"
To get products info:  GET "http://localhost:8080/api/users/{id}"
To create products:   POST "http://localhost:8080/api/users" -d '{ "name": "P1", "price": 100.00 }'
To update products:   PUT "http://localhost:8080/api/users/{id}" -d '{ "name": "P1", "price": 100.00 }'
To delete products: DELETE "http://localhost:8080/api/users/{id}"
*/

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserModelAssembler userModelAssembler;

    //json으로 통신
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> retrieveUser(@PathVariable Long id) {
        return userService.findById(id)
                .map(userModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDto request) {
        User user = userService.createUser(request.getName(), request.getPw());
        UserModel userModel = userModelAssembler.toModel(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);

    }

    @Getter
    @Setter
    static class UserDto {
        @NotNull(message = "id is required")
        @Size(message = "name must be equal to or lower than 300", min = 1, max = 300)
        private String name;

        @NotNull(message = "password is required")
        @Size(message = "name must be equal to or lower than 300", min = 1, max = 300)
        private String pw;
    }
}
