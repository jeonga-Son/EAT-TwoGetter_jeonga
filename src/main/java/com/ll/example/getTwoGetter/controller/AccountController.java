package com.ll.example.getTwoGetter.controller;

import com.ll.example.getTwoGetter.Repository.UserRepository;
import com.ll.example.getTwoGetter.Service.KakaoService;
import com.ll.example.getTwoGetter.Service.MailService;
import com.ll.example.getTwoGetter.Service.UserService;
import com.ll.example.getTwoGetter.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KakaoService kakaoService;

    @Autowired
    private MailService mailService;



    @GetMapping("/login")
    public String login(){

        return "account/login";
    }

    @GetMapping("/register")
    public String register(Model model){
        User user = new User();
        model.addAttribute("userForm", user);
        return "account/register";
    }
    @GetMapping("/find")
    public String find(){
        return "account/find";
    }
    @PostMapping("/find")
    public String findPw(@RequestBody String username, Model model){
        username = username.split("=")[1];
        username = username.replace("%40", "@");
        if(userService.findByUsename(username)!=null){
            User user = userService.findByUsename(username);
            model.addAttribute("message", "메일이 발송되었습니다.");
            mailService.sendMail("aaddss639@naver.com", "dd");
            return "account/find";
        }else{
            model.addAttribute("message", "존재하지 않는 이메일입니다.");
            return "account/find";
        }
    }

    @PostMapping("/register")
    public String registerAccount(@RequestBody User user){
        System.out.println(user);
        userService.save(user);
        return "redirect:/";
    }

    @ResponseBody
    @GetMapping("/kakao")
    public String kakaoCallback(@RequestParam String code) throws IOException {
        String access_Token = kakaoService.getKakaoAccessToken(code);
        System.out.println("controller access_token : " + access_Token);
        return "index";
    }
}
