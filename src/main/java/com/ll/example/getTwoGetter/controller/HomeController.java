package com.ll.example.getTwoGetter.controller;

import com.ll.example.getTwoGetter.Service.UserService;
import com.ll.example.getTwoGetter.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal UserDetails userDetails, Model model){
        if(userDetails != null){
            String username = userDetails.getUsername();
            User user = userService.findByUsename(username);
            System.out.println(user.getNickname());;
            model.addAttribute("user",user);

        }
        else{


        }
        return "index";
    }
}
