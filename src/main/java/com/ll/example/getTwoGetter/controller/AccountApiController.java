package com.ll.example.getTwoGetter.controller;


import com.ll.example.getTwoGetter.Repository.UserRepository;
import com.ll.example.getTwoGetter.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


}
