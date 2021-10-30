package com.example.storeapp.controller;


import com.example.storeapp.assembler.UserModelAssembler;
import com.example.storeapp.entity.User;
import com.example.storeapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



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
    private UserRepository userRepository;

    @Autowired
    private UserModelAssembler userModelAssembler;

    //json으로 통신
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> retrieveUser(@PathVariable String id) {
        return userRepository.findById(id)
                .map(userModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



//    @RequestMapping(value = "/api/login", method = RequestMethod.GET)
//    public String login(Model model) {
//        User user = new User();
//        model.addAttribute("user",user);
//
//        return "login";
//    }

}
