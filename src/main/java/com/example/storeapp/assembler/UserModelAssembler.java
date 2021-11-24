package com.example.storeapp.assembler;


import com.example.storeapp.controller.UserController;
import com.example.storeapp.entity.User;
import com.example.storeapp.representationmodel.UserModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler
        extends RepresentationModelAssemblerSupport<User, UserModel> {
    public UserModelAssembler() {
        super(UserController.class, UserModel.class);
    }


    @Override
    public UserModel toModel(User entity) {
        UserModel userModel = instantiateModel(entity);

        userModel.add(linkTo(methodOn(UserController.class)
        .retrieveUser(entity.getId()))
        .withSelfRel());

        userModel.setId(entity.getId());
        //userModel.setPw(entity.getPw());
        userModel.setName(entity.getName());

        return userModel;
    }


}
