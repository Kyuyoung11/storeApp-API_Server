package com.example.storeapp.assembler;


import com.example.storeapp.controller.UserController;
import com.example.storeapp.entity.User;
import com.example.storeapp.representationmodel.UserModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class UserModelAssembler
        extends RepresentationModelAssemblerSupport<User, UserModel> {
    public UserModelAssembler() {
        super(UserController.class, UserModel.class);
    }


    @Override
    public UserModel toModel(User entity) {
        return null;
    }
}
