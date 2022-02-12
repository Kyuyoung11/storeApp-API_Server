package com.example.storeapp.controller;

import com.example.storeapp.entity.User;
import com.example.storeapp.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;



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


    //json으로 통신
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> retrieveUser(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @RequestMapping(path = "/{name}/exists", method = RequestMethod.GET)
    public boolean retrieveUserbyName(@PathVariable String name) {
        return userService.checkNameExists(name);
    }


    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> loginByName(@RequestBody @Valid UserDto request) {
        if (!userService.checkNameExists(request.getName())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Optional<User> user = userService.loginUser(request.getName(), request.getPw());
        if (!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }



    @RequestMapping(path = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDto request) {
        User user = userService.createUser(request.getName(), request.getPw());
        return ResponseEntity.status(HttpStatus.CREATED).body(user);

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
