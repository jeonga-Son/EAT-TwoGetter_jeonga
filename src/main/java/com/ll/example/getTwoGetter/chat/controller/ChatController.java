package com.ll.example.getTwoGetter.chat.controller;

import com.ll.example.getTwoGetter.login.Service.UserService;
import com.ll.example.getTwoGetter.login.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    UserService userService;
    @GetMapping("/list")
    public String chatList(@AuthenticationPrincipal UserDetails userDetails, Model model){
        if(userDetails != null){
            String username = userDetails.getUsername();
            User user = userService.findByUsename(username);
            model.addAttribute("user",user);
        }
        return "chat/chatList";
    }

}
