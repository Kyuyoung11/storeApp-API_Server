package com.example.storeapp.service;

import com.example.storeapp.entity.User;
import com.example.storeapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;



    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User createUser(String name, String pw) {
        User user = new User();
        user.setName(name);
        user.setPw(pw);
        return userRepository.save(user);
    }


    @Override
    public boolean checkNameExists(String name) {
        if (userRepository.findByName(name).isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public Optional<User> loginUser(String name, String pw) {

        return userRepository.findUserByNameAndPw(name, pw);
    }


}
