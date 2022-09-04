package com.ll.example.getTwoGetter.login.controller;


import com.ll.example.getTwoGetter.login.Repository.UserRepository;
import com.ll.example.getTwoGetter.login.Service.UserService;
import com.ll.example.getTwoGetter.login.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
//url 시작주소가 "/api" 일 경우 해당하는 정보를 json형식으로 리턴한다.
public class AccountApiController {
    @Autowired
    UserService userService;

    
    @GetMapping("/users")
    public List<User> all(@RequestParam(required = false) String username){
        //RequestParam에 username이 따로 없을 때 모든 유저 정보 리스트를 리턴
        if(username == null){
            return userService.findAll();
        }
        //해당하는 user의 정보의 정보를 리턴
        User user = userService.findByUsername(username);
        List<User> users = new ArrayList<>();
        users.add(user);
        return users;
    }

    @DeleteMapping("/users/{username}")
    void deleteAccount(@PathVariable String username){
        //PathVariable로 받은 username을 DB에서 삭제
        User user = userService.findByUsername(username);
        userService.delete(user);
    }

}
