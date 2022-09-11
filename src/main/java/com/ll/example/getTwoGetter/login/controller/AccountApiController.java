package com.ll.example.getTwoGetter.login.controller;


import com.ll.example.getTwoGetter.Board.domain.entity.Board;
import com.ll.example.getTwoGetter.Board.service.BoardService;
import com.ll.example.getTwoGetter.chat.model.ChatInfo;
import com.ll.example.getTwoGetter.chat.service.ChatInfoService;
import com.ll.example.getTwoGetter.login.Repository.UserRepository;
import com.ll.example.getTwoGetter.login.Service.UserService;
import com.ll.example.getTwoGetter.login.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountApiController {
    @Autowired
    UserService userService;

    @Autowired
    BoardService boardService;

    @Autowired
    ChatInfoService chatInfoService;

    @GetMapping("/users")
    public List<User> all(@RequestParam(required = false) String username){

        if(username == null){
            return userService.findAll();
        }
        User user = userService.findByUsername(username);
        List<User> users = new ArrayList<>();
        users.add(user);
        return users;
    }

    @DeleteMapping("/users/{username}")
    void deleteAccount(@PathVariable String username){
        User user = userService.findByUsername(username);
        userService.delete(user);

        List<Board> boards = boardService.findByUsername(user.getNickname());
        if(boards!=null){
            boardService.delete(boards);
        }
        List<ChatInfo> chatInfos = chatInfoService.findByUsername(user.getNickname());
        List<ChatInfo> chatInfos_=chatInfoService.findByPartner(user.getNickname());
        if(chatInfos!=null){
            chatInfoService.delete(chatInfos);
        }
        if(chatInfos_!=null){
            chatInfoService.delete(chatInfos_);
        }
    }

    @GetMapping("/user/{nickname}")
    public Boolean getUserNickname(@PathVariable String nickname){
        User user = userService.findByNickname(nickname);
        if(user==null){
            return true;
        }
        return false;
    }

}
