package com.ll.example.getTwoGetter.login.controller;


import com.ll.example.getTwoGetter.login.Repository.UserRepository;
import com.ll.example.getTwoGetter.login.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountApiController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> all(@RequestParam(required = false) String username){

        if(username == null){
            return userRepository.findAll();
        }
        User user = userRepository.findByUsername(username);
        List<User> users = new ArrayList<>();
        users.add(user);
        return users;
    }

    @DeleteMapping("/users/{username}")
    void deleteAccount(@PathVariable String username){
        User user = userRepository.findByUsername(username);
        userRepository.delete(user);
    }

}
