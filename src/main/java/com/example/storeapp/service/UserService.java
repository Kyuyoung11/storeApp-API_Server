package com.example.storeapp.service;

import com.example.storeapp.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);

    User createUser(String id, String pw);

    boolean checkNameExists(String name);
}
